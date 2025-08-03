package org.nauka.model;

import org.nauka.model.dto.StudentDto;

import java.util.List;

import static org.nauka.model.StudentDaoTestData.semesterSummer;
import static org.nauka.model.StudentDaoTestData.semesterWinter;

public class StudentDtoTestData {
    public static StudentDto adamKowalskiDto() {
        return new StudentDto("Adam", 112233L, "112233@student.wwe.pl", List.of(semesterSummer, semesterWinter));
    }

    public static StudentDto alaKotWithoutId() {
        return new StudentDto(null, 114477L, "114477@student.wwe.pl", List.of(semesterWinter));
    }
}

