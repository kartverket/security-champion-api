CREATE TABLE IF NOT EXISTS securityChampions (
    id  VARCHAR(60) PRIMARY KEY DEFAULT gen_random_uuid(),
    email  VARCHAR(254) UNIQUE
 );

CREATE TABLE IF NOT EXISTS repositories (
    id  VARCHAR(60) PRIMARY KEY DEFAULT gen_random_uuid(),
    repoName VARCHAR(254) UNIQUE
);

CREATE TABLE IF NOT EXISTS securityChampion_repositories (
    secChampEmail VARCHAR(254),
    repoId VARCHAR(60) PRIMARY KEY,
    CONSTRAINT fk_secChampEmail FOREIGN KEY (secChampEmail) REFERENCES securityChampions(email) ON DELETE CASCADE,
    CONSTRAINT fk_repoId FOREIGN KEY (repoId) REFERENCES repositories(id) ON DELETE CASCADE
);
