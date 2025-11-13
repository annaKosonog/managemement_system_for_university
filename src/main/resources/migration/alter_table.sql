ALTER TABLE semester_direction
    ADD CONSTRAINT fk_semester_direction_semester
        FOREIGN KEY (semester_id) REFERENCES semester (semester_id);

ALTER TABLE semester_direction
    ADD CONSTRAINT fk_semester_direction_tuition_fee
        FOREIGN KEY (tuition_fee_id) REFERENCES tuition_fee (tuition_fee_id);