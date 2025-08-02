package org.nauka.model;

import org.nauka.model.dto.StudentDto;

public class StudentDtoTestData {
    public static StudentDto adamKowalskiDto() {
        return new StudentDto("Adam", 112233L, "112233@student.wwe.pl");
    }

    public static StudentDto alaKotWithoutId() {
        return new StudentDto("Ala", 114477L, "114477@student.wwe.pl");
    }
}

