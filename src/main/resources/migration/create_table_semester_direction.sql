CREATE TABLE IF NOT EXISTS semesterDirection
(
    idSemesterDirection bigserial primary key,
    direction           varchar,
    semester_id         bigint not null,
    student_id          bigint not null,
    tuitionFee_id       bigint not null,

    constraint fk_semesterDirection_student foreign key (student_id) references student(idStudent)

);
