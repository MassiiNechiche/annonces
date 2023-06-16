-- V1__Create_Annonce_Table.sql

CREATE TABLE if not exists annonce (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    prix DECIMAL(10, 2) NOT NULL,
    type VARCHAR(20) NOT NULL
);

-- Add constraints for the "type" column
ALTER TABLE annonce
    ADD CONSTRAINT annonce_type CHECK (type IN ('Immobilier', 'véhicule', 'emploi'));
