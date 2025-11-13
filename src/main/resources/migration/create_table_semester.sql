CREATE TABLE IF NOT EXISTS semester
(
    semester_id           BIGSERIAL PRIMARY KEY,
    name                 varchar(15) NOT NULL,
    start_date            date        not null,
    end_date              date        NOT NULL,
    semester_direction_id bigint      not null,

    constraint fk_semester_semester_directon foreign key (semester_direction_id) references semester_direction (id_semester_direction)
);
