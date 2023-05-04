-- liquibase formatted sql
-- changeset PavelSemenyuk:1

CREATE TABLE newStudent
(
    id  SERIAL,
    age SERIAL
)

-- changeset PavelSemenyuk:2
ALTER TABLE newStudent
    ADD COLUMN name TEXT;

-- changeset PavelSemenyuk:3
CREATE INDEX users_name_student ON newStudent (name);