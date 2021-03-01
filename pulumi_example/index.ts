import * as digitalocean from "@pulumi/digitalocean";
import * as pulumi from "@pulumi/pulumi";
import * as provisioners from "./provisioners";
const fs = require('fs')
import { getFileHash } from "./util";

const config = new pulumi.Config();

const sshkey_docker = new digitalocean.SshKey("docker", {publicKey: fs.readFileSync("ssh/id_rsa.pub").toString()});

const droplet = new digitalocean.Droplet("docker", {
  image: "ubuntu-20-04-x64",
  region: digitalocean.Regions.FRA1,
  size: digitalocean.DropletSlugs.DropletS1VCPU1GB,
  sshKeys: [sshkey_docker.fingerprint],
});

const domain = new digitalocean.Domain("domain", {
  name: config.get("domain"),
  ipAddress: droplet.ipv4Address,
}, {dependsOn: droplet})

const wildcard = new digitalocean.DnsRecord("wildcard", {
  name: "*",
  domain: domain.name,
  type: "A",
  value: droplet.ipv4Address,
}, {dependsOn: domain});

const conn: provisioners.ConnectionArgs = {
  host: droplet.ipv4Address,
  username: "root",
  privateKey: fs.readFileSync("ssh/id_rsa").toString()
};

const changeToken = getFileHash("/docker/docker-compose.yml");

const copyComposeConfig = new provisioners.CopyFile("Copy compose - " + changeToken.substring(0,4), {
  conn,
  changeToken,
  src: "docker/docker-compose.yml",
  dest: "/home/docker/docker-compose.yml",
}, { dependsOn: droplet });

const updateApt = new provisioners.RemoteExec("update-apt", {
  conn,
  command: "sudo apt-get update",
}, { dependsOn: copyComposeConfig,  })

const toolsConfig = new provisioners.RemoteExec("install-tools", {
  conn,
  command: "sudo apt-get install vim apt-transport-https ca-certificates curl gnupg-agent software-properties-common -y",
}, { dependsOn: updateApt })

const dockerGPGConfig = new provisioners.RemoteExec("install-docker-GPG-key", {
  conn,
  command: "sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -",
}, { dependsOn: toolsConfig })

const dockerRepoConfig = new provisioners.RemoteExec("install-docker-repo", {
  conn,
  command: 'sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"',
}, { dependsOn: dockerGPGConfig })

const dockerConfig = new provisioners.RemoteExec("install-docker", {
  conn,
  command: "sudo apt-get update && sudo apt-get install docker-ce docker-ce-cli containerd.io -y",
}, { dependsOn: [dockerRepoConfig]})

const dockerComposeConfig = new provisioners.RemoteExec("install-docker-compose", {
  conn,
  command: 'sudo curl -L "https://github.com/docker/compose/releases/download/1.26.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose && sudo chmod +x /usr/local/bin/docker-compose',
}, { dependsOn: [dockerConfig]})

const dockerComposeContainersConfig = new provisioners.RemoteExec("Run docker commmand - "+ changeToken.substring(0,4), {
  changeToken,
  conn,
  command: "sudo docker-compose -f /home/docker/docker-compose.yml up -d --no-recreate --remove-orphans",
}, { dependsOn: [dockerComposeConfig, copyComposeConfig]})

export const ip = droplet.ipv4Address;