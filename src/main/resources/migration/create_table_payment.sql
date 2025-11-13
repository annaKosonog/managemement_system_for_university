CREATE TABLE IF NOT EXISTS payment
(
    paymentId BIGSERIAL PRIMARY KEY,
    student_id BIGINT NOT NULL ,
    paymentDate date NOT NULL ,
    amount decimal(5,2),
    tuition_fee_id BIGINT NOT NULL,


    CONSTRAINT fk_payment_student FOREIGN KEY (student_id) REFERENCES student(idStudent),
    CONSTRAINT fk_payment_tuitionFee FOREIGN KEY (tuition_fee_id) REFERENCES tuitionFee(tuitionFeeId)
);