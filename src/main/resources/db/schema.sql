CREATE TABLE IF NOT EXISTS securityChampions (
    id  VARCHAR(60) PRIMARY KEY,
    email   VARCHAR(60)
 );

CREATE TABLE IF NOT EXISTS repositories (
    id  VARCHAR(60) PRIMARY KEY,
    repoName   VARCHAR(60)
    );

CREATE TABLE IF NOT EXISTS securityChampion_repository (
    repoID VARCHAR(60),
    secChampID VARCHAR(60),
    CONSTRAINT repo FOREIGN KEY (repoID)  REFERENCES repositories(id),
    CONSTRAINT securityChampion FOREIGN KEY (secChampID) REFERENCES securityChampions(id)

    );
