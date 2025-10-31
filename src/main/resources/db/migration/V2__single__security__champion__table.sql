BEGIN;
CREATE TABLE IF NOT EXISTS securityChampion
(
    id         VARCHAR(60) PRIMARY KEY DEFAULT gen_random_uuid(),
    email      VARCHAR(254),
    repository VARCHAR(254) UNIQUE
);


INSERT INTO securitychampion (email, repository)
SELECT email, repoName
FROM securityChampion_repositories
         JOIN securityChampions ON securityChampion_repositories.secChampEmail = securityChampions.email
         JOIN repositories ON securityChampion_repositories.repoId = repositories.id;

DROP TABLE securityChampion_repositories;
DROP TABLE repositories;
DROP TABLE securityChampions;

COMMIT;