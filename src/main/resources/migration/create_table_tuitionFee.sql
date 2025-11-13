CREATE TABLE IF NOT EXISTS tuition_fee
(
    tuition_fee_id         bigserial primary key,
    student_id           bigint          not null,
    semester_id          bigint          not null,
    semester_direction_id bigint          not null,
    amount               decimal,
    payment_due_date       date,
    payment_status      varchar(15) not null default 'NOT_PAID',


    CONSTRAINT fk_tuitionfee_student FOREIGN KEY (student_id) REFERENCES student (id_student),
    CONSTRAINT fk_tuitionfee_semester FOREIGN KEY (semester_id) REFERENCES semester (semester_id),
    CONSTRAINT fk_tuitionfee_semesterdirection FOREIGN KEY (semester_direction_id) REFERENCES semester_direction (id_semester_direction)
);



