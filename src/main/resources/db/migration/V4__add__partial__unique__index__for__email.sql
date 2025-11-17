BEGIN;
CREATE UNIQUE INDEX idx_security_champion_email_null_repo
    ON securityChampion (email)
    WHERE repository IS NULL;
COMMIT;