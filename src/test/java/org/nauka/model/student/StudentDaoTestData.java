package org.nauka.model.student;

import org.nauka.model.dao.Student;

import java.util.List;

import static org.nauka.model.SemesterTest.semesterSummer;
import static org.nauka.model.SemesterTest.semesterWinter;

public class StudentDaoTestData {

    public static Student adamKowalskiWithId() {
        return new Student(1L, "Adam", 112233L, "112233@student.wwe.pl", List.of(semesterSummer, semesterWinter));
    }

    public static Student alaKotWithId() {
        return new Student(2L, "Ala", 114477L, "114477@student.wwe.pl", List.of(semesterWinter));
    }
}
