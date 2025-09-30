package org.nauka.model;

import org.nauka.model.dao.Semester;

import java.time.LocalDate;

public interface SemesterTest {
    LocalDate startSemestersSummer = LocalDate.of(2025, 3, 1);
    LocalDate endSemestersSummer = LocalDate.of(2025, 9, 30);
    LocalDate startSemesterWinter = LocalDate.of(2024, 10, 1);
    LocalDate endSemesterWinter = LocalDate.of(2025, 2, 28);

    Semester semesterSummer = new Semester("letni", startSemestersSummer, endSemestersSummer);
    Semester semesterWinter = new Semester("zimowy", startSemesterWinter, endSemesterWinter);
}
