# Security Champion API

Dette er en tjeneste for å lagre security champions for hvert GitHub-repository (les. backstagekomponent). 
Hensikten er å gi en god oversikt over hvem som er security champion og for å kunne enkelt administrere security champions direkte i Backstage. 
Dette APIet brukes kun av [Kartverket.dev](https://github.com/kartverket/kartverket.dev) og er kun tilgjengelig internt på SKIP og har ikke noen ingress for andre brukere eller tjenester.

Frontend for tjenesten er en backstage-plugin som man finner her [Security champion plugin](https://github.com/kartverket/kartverket.dev/tree/main/plugins/security-champion).

## Bygg og kjør tjenesten lokalt

### Databaseoppsett
Tjenesten er satt opp med PostgreSQL som databasesystem og bruker flyway for databasemigreringer. 
Databasen kjøres opp med Docker, hvis du ikke har Docker installert på maskinen, last ned en docker daemon (f.eks colima) og docker-compose.

### Hvordan bygge tjenesten
Tjenesten er en spring applikasjon skrevet i Kotlin med Gradle som byggeverktøy. Fra kommandolinjen kan applikasjonen bygges med `./gradlew build`. 

Brukes intellij kan applikasjonen også bygges fra Gradle menyen. 

Det er også en Dockerfile på rotnivå som bygger tjenesten slik at den kan kjøres på SKIP.

### Hvordan kjøre tjenesten
**Terminal**
Start starter enkelt med `./gradlew bootRun --args='--spring.profiles.active=local'`.

**IntelliJ**
- Lag en ny Run/Debug Configuration for Spring Boot Application.
- Velg `SecurityChampionApplication` som main class.
- Set "active profile" til "local".
- Kjør med Play-knappen i IntelliJ.
[]()
Spring Boot starter databasen automatisk gjennom `docker-compose.yaml`.

Typisk feil på macmaskiner er at `docker`/`docker compose` ikke er tilgjengelig i `PATH` for IntelliJ-prosessen. Legg til følgende environment variable i IntelliJ Run/Debug Configuration for å fikse det:

```bash
PATH=/opt/homebrew/bin:$PATH[]()
```

## Deployment og databaser i produksjon

Deployments blir håndtert gjennom [skvis-apps](https://github.com/kartverket/skvis-apps)

Databaser i produksjon blir håndtert gjennom [skvis-terraform](https://github.com/kartverket/skvis-terraform)
