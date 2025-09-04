CREATE TABLE IF NOT EXISTS securityChampions (
    id  VARCHAR(60) PRIMARY KEY,
    email   VARCHAR(60)
 );

CREATE TABLE IF NOT EXISTS repositories (
    id  VARCHAR(60) PRIMARY KEY,
    repoName   VARCHAR(60),
    secChampId VARCHAR(60),
    CONSTRAINT securityChampion FOREIGN KEY (secChampId) REFERENCES securityChampions(id)
);
