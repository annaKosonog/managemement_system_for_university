CREATE TABLE IF NOT EXISTS student
(
    id_student   BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    index_number BIGINT       NOT NULL UNIQUE,
    email       VARCHAR(255) NOT NULL
);


