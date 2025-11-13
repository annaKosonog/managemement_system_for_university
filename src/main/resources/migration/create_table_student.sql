CREATE TABLE IF NOT EXISTS student
(
    idStudent   BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    indexNumber BIGINT       NOT NULL UNIQUE,
    email       VARCHAR(255) NOT NULL
);


