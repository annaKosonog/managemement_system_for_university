CREATE TABLE IF NOT EXISTS semester_direction
(
    id_semester_direction bigserial primary key,
    direction           varchar,
    semester_id         bigint not null,
    student_id          bigint not null,
    tuition_fee_id       bigint not null,

    constraint fk_semester_direction_student foreign key (student_id) references student(id_student)

);
