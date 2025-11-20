CREATE TABLE IF NOT EXISTS student_details
(
    id_student_details    BIGSERIAL PRIMARY KEY,
    id_student            BIGINT NOT NULL,
    id_semester           BIGINT NOT NULL UNIQUE,
    id_semester_direction BIGINT NOT NULL UNIQUE,
    id_tuition_fee        BIGINT NOT NULL UNIQUE,

    constraint fk_student_details_student FOREIGN KEY (id_student) references student (id_student),
    constraint fk_student_details_semester foreign key (id_semester) references semester (semester_id),
    constraint fk_student_details_semester_direction foreign key (id_semester_direction) references semester_direction (id_semester_direction),
    constraint fk_student_details_tuition_fee foreign key (id_tuition_fee) references tuition_fee (tuition_fee_id)
);