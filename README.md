# Security Champion API

Dette er en tjeneste for å håndtere security champions for hvert repo i github. Hensikten er å gi en god oversikt over hvem som er security champion og å kunne endre security champions for hver ressurs direkte i backstage. Tjenesten brukes av [security champion plugin](https://github.com/kartverket/security-champion-plugin) for at [Kartverket.dev](https://github.com/kartverket/kartverket.dev), utviklerportalen til Kartverket, kan ta tjenesten i bruk.

## Bygg og kjør tjenesten lokalt

### Database
Tjenesten er satt opp med PostgreSQL som databasesystem og bruker flyway for databasemigreringer. Hvis du ikke har det installert på maskinen, last ned en docker deamon (f.eks colima) og docker-compose.

```
brew install docker
brew install docker-compose
brew install colima
```

Deretter kjør:

```
colima start
docker-compose up
```

### Hvordan bygge tjenesten
Tjenesten er en spring applikasjon skrevet i Kotlin med Gradle som byggeverktøy. Fra kommandolinjen kan applikasjonen bygges med `./gradlew build`. Brukes intellij kan applikasjonen også bygges fra Gradle menyen. Det er også en Dockerfile på rotnivå som bygger og kjører tjenesten.

### Hvordan kjøre tjenesten
I intellij kan applikasjonen kjøres ved å kjøre main klassen. Fra kommandolinjen kan applikasjonen kjøres med `./gradlew bootRun`



## API Schema

Under finnes en oversikt over API endepunktene til security champion API.

````json
{
    "info": {
        "title": "Security Champion API",
        "baseUrl": "/api",
    },

    "paths": {
        "/securityChampion" : {
            "post": {
                "summary": "get security champions"
            }
            "requestBody": {
                "required": true,
                "content": {
                    "application/json": {
                    "schema": {
                        "type": "array",
                        }
                    "items": {
                        "type": "string"
                        }
                    }
                }   
            }
        }
    }

    "paths": {
        "/setSecurityChampion" : {
            "post": {
                "summary": "set a security champion"
            }
            "requestBody": {
                "required": true,
                "content": {
                    "application/json": {
                    "schema": {
                        "type": "string",
                        }
                    }
                }   
            }
        }
    }
}

````


