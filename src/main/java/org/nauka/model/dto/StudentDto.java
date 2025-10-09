package org.nauka.model.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class StudentDto {
    private String name;
    private Long indexNumber;
    private String email;
    private List<Long> paymentIds;
    private List<Long> semesterDirectionIds;

    public static StudentDto of(String name, java.lang.Long indexNumber, String e_mail, List<Long> semesterDirections) {
        StudentDto studentDto = new StudentDto();
        studentDto.name = name;
        studentDto.indexNumber = indexNumber;
        studentDto.email = e_mail;
        //   studentDto.semesters = semesters;
        studentDto.paymentIds = null;
        studentDto.semesterDirectionIds = semesterDirections;
        return studentDto;
    }
}
