INSERT INTO student (name, index_number, email)
VALUES ('Jan Kowalski', 12345, 'jan.kowalski@uczelnia.pl'),
       ('Anna Nowak', 12346, 'anna.nowak@uczelnia.pl'),
       ('Tomek Kowalski', 12347, 'tomek.kowalski@uczelnia.pl'),
       ('Wiktoria Test', 12348, 'wiktoria.test@uczelnia.pl');

INSERT INTO semester (name, startDate, endDate, semesterDirection_id)
VALUES ('Semestr 1', '2025-10-01', '2026-01-31', 1),
       ('Semestr 1', '2025-10-01', '2026-01-31', 1),
       ('Semestr 2', '2025-03-01', '2026-09-31', 2),
       ('Semestr 2', '2025-03-01', '2026-09-31', 2);

INSERT INTO semesterDirection (direction, student_id)
VALUES ('Informatyka', 1),
       ('Matematyka', 2);

INSERT INTO tuitionFee (student_id, semester_id, semester_direction_id, amount)
VALUES (1, 1, 1, 1200.00),
       (2, 2, 2, 1300.00),
       (3, 2, 2, 1300.00),
       (4, 1, 1, 1200.00);

INSERT INTO payment (student_id, payment_date, amount)
VALUES (1, '2025-09-30', 1200.00),
       (2, '2025-02-15', 1300.00),
       (3, '2025-02-15', 1300.00),
       (4, '2025-02-15', 1200.00);