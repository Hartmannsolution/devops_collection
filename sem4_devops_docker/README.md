# Opsætning af droplet
1. Opret en konto hos digitalocean.com og log in
2. Tryk på Droplets i fanen i venstre side
3. Tryk på Create Droplet, eller hvis du har droplet i forvejen, så tryk på create i højre hjørne.
4. Tryk på fanen Marketplace
5. Søg efter og vælg Docker
6. Tjek at Basic plan er valgt
7. Vælg den mindste droplet - 5$/mo
8. Vælg Frankfurt datacenter
9. Tryk på New SSH Key
10. Kopier public key info ind (Det forventes at I har en SSH nøgle lokalt på computeren til brug her)
11. Tryk Add SSH Key
12. Tjek at den nye SSH Key er valgt
13. lad resten af indstillingerne være standard og scroll ned til bunden og tryk Create Droplet

# Opsætning af domain
1. Log in på digitalocean.com
2. Tryk på networking i venstre side
3. Indtast dit domain i boksen og tryk Add Domain, eller hvis du har et domain i forvejen, så klik på dette og slet dine records, som peger på forrige droplet.
4. Under hostname indtast *, vælg den nyoprettet droplet som der skal direct til og tryk på Add Record


# Opsætning af docker
1. Clone dette repo
2. Log in på droplet via ssh
3. Kør kommando'en "docker login" og indtast login oplysninger
3. Kør kommando'en "docker network create dev" (kør kommando'en "docker network ls" for at se om netværket er oprettet)
4. Opret en mappe som hedder docker under /home eller kør "sudo mkdir -p /home/docker/config" og spring step 5 over.
5. Opret en mappe som hedder config under /home/docker
6. kopier docker-compose filen fra Docker_config_Traefik op på dropletten i /home/docker/config mappen (I kan f.eks. bruge filezilla eller scp, hvis I har linux)
7. kør kommando'en "docker-compose up -d" fra mappen /home/docker/config

Vær ekstra opmærksom på Watchtower konfigurationen med hensyn til "--interval". Lige nu står den til 600, hvilket betyder at den tjekker og henter nye images hver 10. minut.
Denne indstilling skal vurderes om den skal sættes op eller ned, men husk at man kun har 200 pulls pr 6. time hos DockerHub og 1 container er en pull.

# Opsætning af ny container
1. brug Example_container_traefik docker-compose filen til at lave en ny docker-compose fil
skift følgende:
* container navn fra "whoami" til det du gerne vil have
* imagename til det nye image som er pushet op (tag imagename fra dit repository på dockerhub - f.eks name/imagename)
* SOME_SUB_DOMAIN (dette navn bestemmer du, og skal være unik fra tidligere konfigurationer. Dette er til opsætning af Traefik)
* SOME_SUB_DOMAIN.MY_DOMAIN_NAME.com (Du bestemmer SOME_SUB_DOMAIN navnet, men Y_DOMAIN_NAME.com skal være det du har oprettet. URL'en her bliver routen du tilgår container på udefra)
2. Opret en mappe under /home/docker med et sigende navn
3. Kopier docker-compose filen op på dropletten i den nye mappe
4. Kør kommando'en "docker-compose up -d" i den nye mappe

Da man har et max antal pulls per 6. time hos DockerHub, så er det vigtigt at man sætter følgende label på containers, som ikke skal overvåges og opdateres(f.eks databaser):
labels:
      - "com.centurylinklabs.watchtower.enable=false"

I kan se eksempel på dette i traefik konfigurationen

# Load balancing
Jeg har givet et eksempel på at lave load balancing i en compose fil med en gamle version (2.2), men man kan også load balance i nyere versioner af docker-compose.
Der skal bruges denne kommando:
docker-compose up --scale SERVICE_NAME=3 -d