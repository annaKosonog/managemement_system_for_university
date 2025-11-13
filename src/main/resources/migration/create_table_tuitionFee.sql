CREATE TYPE IF NOT EXISTS "paymentStatus" AS ENUM (
    'PAID',
    'PENDING',
    'OVERDUE',
    'PARTIAL',
    'NOT_PAID'
);


CREATE TABLE IF NOT EXISTS tuitionFee
(
    tuitionFeeId         bigserial primary key,
    student_id           bigint          not null,
    semester_id          bigint          not null,
    semesterDirection_id bigint          not null,
    amount               decimal,
    paymentDueDate       date,
    "paymentStatus"      "paymentStatus" not null default 'NOT_PAID',


    CONSTRAINT fk_tuitionfee_student FOREIGN KEY (student_id) REFERENCES student (idStudent),
    CONSTRAINT fk_tuitionfee_semester FOREIGN KEY (semester_id) REFERENCES semester (semesterId),
    CONSTRAINT fk_tuitionfee_semesterdirection FOREIGN KEY (semesterDirection_id) REFERENCES semesterDirection (idSemesterDirection)
);



