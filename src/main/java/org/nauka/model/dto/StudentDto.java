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


    public static StudentDto of(String name, java.lang.Long indexNumber, String e_mail) {
        StudentDto studentDto = new StudentDto();
        studentDto.name = name;
        studentDto.indexNumber = indexNumber;
        studentDto.email = e_mail;
        //   studentDto.semesters = semesters;
        studentDto.paymentIds = null;
        return studentDto;
    }
}
