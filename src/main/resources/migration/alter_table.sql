ALTER TABLE semesterDirection
    ADD COLUMN semester_id BIGINT;
ALTER TABLE semesterDirection
    ADD CONSTRAINT fk_semester_direction_semester
        FOREIGN KEY (semester_id) REFERENCES semester (semesterId);

ALTER TABLE semesterDirection
    ADD COLUMN tuition_fee_id BIGINT;
ALTER TABLE semesterDirection
    ADD CONSTRAINT fk_semester_direction_tuition_fee
        FOREIGN KEY (tuiótion_fee_id) REFERENCES tuitionFee (tuitionFeeId);