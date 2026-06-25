# Security Champion API

Dette er en tjeneste for å håndtere security champions for hvert GitHub-repository. Hensikten er å gi en god oversikt over hvem som er security champion og å kunne endre security champions for hver ressurs direkte i Backstage. Tjenesten brukes av [Kartverket.dev](https://github.com/kartverket/kartverket.dev) og er kun tilgjengelig internt og har ikke ingress for andre brukere eller tjenester.

## Bygg og kjør tjenesten lokalt

### Databaseoppsett
Tjenesten er satt opp med PostgreSQL som databasesystem og bruker flyway for databasemigreringer. Hvis du ikke har det installert på maskinen, last ned en docker daemon (f.eks colima) og docker-compose.

### Hvordan bygge tjenesten
Tjenesten er en spring applikasjon skrevet i Kotlin med Gradle som byggeverktøy. Fra kommandolinjen kan applikasjonen bygges med `./gradlew build`. 

Brukes intellij kan applikasjonen også bygges fra Gradle menyen. Det er også en Dockerfile på rotnivå som bygger og kjører tjenesten.

### Hvordan kjøre tjenesten
I IntelliJ kan applikasjonen kjøres ved å kjøre main klassen. Fra kommandolinjen kan applikasjonen kjøres med `./gradlew bootRun`.

Hvis du kjører med Play-knappen i IntelliJ og forventer at Spring Boot skal starte `docker-compose.yaml` automatisk, må `docker`/`docker compose` være tilgjengelig i `PATH` for IntelliJ-prosessen.

Legg til følgende environment variable i IntelliJ Run/Debug Configuration:

```bash
PATH=/opt/homebrew/bin:$PATH
```

## Deployment og databaser i produksjon

Deployments blir håndtert gjennom [skvis-apps](https://github.com/kartverket/skvis-apps)

Databaser i produksjon blir håndtert gjennom [skvis-terraform](https://github.com/kartverket/skvis-terraform)

## Tracing (OpenTelemetry)

Applikasjonen bruker `opentelemetry-spring-boot-starter` for å eksportere traces

Lokal profil (`application-local.yaml`) har `otel.sdk.disabled: true` som standard, slik at lokal kjøring ikke prøver a sende traces til en manglende collector.
