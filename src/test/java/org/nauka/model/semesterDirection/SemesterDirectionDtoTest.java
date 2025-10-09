package org.nauka.model.semesterDirection;

import org.nauka.model.dto.SemesterDirectionDto;

import java.util.List;

public class SemesterDirectionDtoTest {
    public static SemesterDirectionDto administrationDto() {
        return new SemesterDirectionDto(1L, "ADMINISTRATION", List.of(1L), List.of());
    }

    public static SemesterDirectionDto computerScienceDto() {
        return new SemesterDirectionDto(2L, "COMPUTER SCIENCE", List.of(1L), List.of());
    }
}
