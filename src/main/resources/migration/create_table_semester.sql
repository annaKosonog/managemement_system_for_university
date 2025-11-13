CREATE TABLE IF NOT EXISTS semester
(
    semesterId           BIGSERIAL PRIMARY KEY,
    name                 varchar(15) NOT NULL,
    startDate            date        not null,
    endDate              date        NOT NULL,
    semesterDirection_id bigint      not null,

    constraint fk_semester_semester_directon foreign key (semesterDirection_id) references semesterDirection (idSemesterDirection)
);
