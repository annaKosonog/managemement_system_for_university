package org.nauka.model;

import org.nauka.model.dao.Semester;
import org.nauka.model.dto.SemesterDto;

import java.time.LocalDate;
import java.util.List;

public interface SemesterTest {
    LocalDate startSemestersSummer = LocalDate.of(2025, 3, 1);
    LocalDate endSemestersSummer = LocalDate.of(2025, 9, 30);
    LocalDate startSemesterWinter = LocalDate.of(2024, 10, 1);
    LocalDate endSemesterWinter = LocalDate.of(2025, 2, 28);

    Semester semesterSummer = new Semester("letni", startSemestersSummer, endSemestersSummer);

    Semester semesterSummerDao = new Semester(1L, "letni", startSemestersSummer, endSemestersSummer, List.of(), null, List.of());
    Semester semesterWinter = new Semester("zimowy", startSemesterWinter, endSemesterWinter);

    SemesterDto semesterSummerDto = new SemesterDto(1L, "letni", startSemestersSummer, endSemestersSummer, List.of());

    SemesterDto semesterWinterDto = new SemesterDto(2L, "zimowy", startSemesterWinter, endSemestersSummer, List.of());
}
