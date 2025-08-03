package org.nauka.model;

import org.nauka.model.dao.Semester;
import org.nauka.model.dao.Student;

import java.time.LocalDate;
import java.util.List;

public class StudentDaoTestData {
    private static final LocalDate startSemestersSummer = LocalDate.of(2025, 3, 1);
    private static final LocalDate endSemestersSummer = LocalDate.of(2025, 9, 30);
    public static final LocalDate startSemesterWinter = LocalDate.of(2024, 10, 1);
    public static final LocalDate endSemesterWinter = LocalDate.of(2025, 2, 28);

    static Semester semesterSummer = new Semester(1L, "letni", startSemestersSummer, endSemestersSummer);
    static Semester semesterWinter = new Semester(2L, "letni", startSemesterWinter, endSemesterWinter);

    public static Student adamKowalskiWithoutId() {
        return new Student(null,"Adam", 112233L, "112233@student.wwe.pl", List.of(semesterSummer, semesterWinter));
    }

    public static Student adamKowalskiWithId() {
        return new Student(1L, "Adam", 112233L, "112233@student.wwe.pl", List.of(semesterSummer, semesterWinter));
    }

    public static Student alaKot() {
        return new Student(2L, "Ala", 114477L, "114477@student.wwe.pl", List.of(semesterSummer));
    }
}
