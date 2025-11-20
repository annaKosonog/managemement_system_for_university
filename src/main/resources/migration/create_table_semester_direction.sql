CREATE TABLE IF NOT EXISTS semester_direction
(
    id_semester_direction bigserial primary key,
    direction             varchar,
    semester_id           bigint not null,
    tuitionFee_id         bigint not null

);
