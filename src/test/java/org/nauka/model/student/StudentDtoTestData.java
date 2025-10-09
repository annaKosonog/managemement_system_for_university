package org.nauka.model.student;

import org.nauka.model.dto.StudentDto;

import java.util.List;

public class StudentDtoTestData {
    public static StudentDto adamKowalskiDto() {
        return new StudentDto("Adam", 112233L, "112233@student.wwe.pl", List.of(), List.of(2L));
    }

    public static StudentDto alaKotDto() {
        return new StudentDto("Ala", 114477L, "114477@student.wwe.pl", List.of(), List.of(1L));
    }
}