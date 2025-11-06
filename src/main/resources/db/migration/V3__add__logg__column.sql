BEGIN;
ALTER TABLE securityChampion
ADD lastModifiedBy VARCHAR(254);
COMMIT;