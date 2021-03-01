# pulumi_example

1. Opret en konto på https://www.pulumi.com og generate en Access Token. Gem denne til når kommando'en 'pulumi up' skal bruges.
2. Opret en konto på https://www.digitalocean.com og generate en Access Token. Gem denne til, når config skal sættes op.
3. Git clone dette repo lokalt.
4. Åben folderen i VS Code og vælg at reopen i container. Vent til installation af containeren er færdig.
5. Kør ssh-keygen -t rsa -m PEM -f ssh/id_rsa fra terminalen i containeren. Tjek at de 2 nøgler er blevet oprettet i .ssh folderen (Man kan også bruge sine egne, men de skal være med PEM. Kør eventuelt 'ssh-keygen -m PEM -t id_rsa' på en eksisterende nøgle).
6. kør 'pulumi config set domain YOURDOMAIN' i containeren.
7. kør 'pulumi config set digitalocean:token XXXXXXXXXXXXXX --secret' i containeren.
8. kør 'pulumi up' og indtast pulumi access token. Sig ja til at der skal rettes en stack osv, Sig ja til at den skal perform en update. vent til den er færdig.
9. Man kan nu bruge ssh til at logge ind i dropletten. 'ssh root@IP'.
10. kør 'pulumi destroy' for at fjerne alt igen.

a. Hvis den fejler et trin, så kør 'pulumi up' igen og den vil fortsætte, hvor den slap.

b. Hvis man bliver nødt til at afbryde den imens den er igang og den brokker sig over 'pending operation', så kør 'pulumi stack export | pulumi stack import' og prøv igen.


# Opsætning af containers
1. Under containeren traefik i labels /docker/docker-compose-yml ændre mailen adressen EXAMPLE@EXAMPLE.com til en anden ellers vil automatisk tls certificate ikke virke
2. Der er en eksempel på i docker-compose.yml filen, hvordan et load balancing scenarie med automatisk opsætning af reverse proxy og tls certificate vil se ud.
Man kan fjerne scale: 2 fra container, hvis man ikke vil load balance.
BEMÆRK at scale: kun virker i docker-compose v2.2, så hvis man vil bruge en nyere version skal denne fjernes fra filen.
3. for at lave en ny container skal man blot kopiere eksemplet fra docker-compose filen og ændre domainet, samt hvor der står CHANGEME.
4. Jeg anbefaler at for hver ny service/container man vil have kørende at man laver en compose fil, kopiere den op på dropletten under sin egen folder, hvorefter der skal køres docker-compose up -d.
Traefik finder selv ud af at opdage containeren, trække et certificat og dirigere trafikken hen til containeren med de informationer man har sat på labels.
BEMÆRK at det er vigtigt at man i sin dockerfil bruger EXPOSE ellers ved traefik ikke hvilken port den skal sende trafikken hen til.
Man kan også bruge ports under container i docker-compose, hvis man har glemt EXPOSE i dockerfile
