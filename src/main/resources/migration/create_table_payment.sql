CREATE TABLE IF NOT EXISTS payment
(
    payment_id BIGSERIAL PRIMARY KEY,
    student_id BIGINT NOT NULL ,
    payment_date date NOT NULL ,
    amount decimal(5,2),
    tuition_fee_id BIGINT NOT NULL,


    CONSTRAINT fk_payment_student FOREIGN KEY (student_id) REFERENCES student(id_student),
    CONSTRAINT fk_payment_tuitionFee FOREIGN KEY (tuition_fee_id) REFERENCES tuition_fee(tuition_fee_id)
);