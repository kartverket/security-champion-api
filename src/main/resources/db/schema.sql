CREATE TABLE IF NOT EXISTS securityChampions (
    id  VARCHAR(60) PRIMARY KEY DEFAULT gen_random_uuid(),
    email  VARCHAR(60) UNIQUE
 );

CREATE TABLE IF NOT EXISTS repositories (
    id  VARCHAR(60) PRIMARY KEY DEFAULT gen_random_uuid(),
    repoName VARCHAR(60) UNIQUE,
    secChampEmail VARCHAR(60),
    CONSTRAINT securityChampion FOREIGN KEY (secChampEmail) REFERENCES securityChampions(email)
);
