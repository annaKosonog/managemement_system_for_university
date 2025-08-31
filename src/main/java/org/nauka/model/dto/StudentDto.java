package org.nauka.model.dto;

import lombok.*;
import org.nauka.model.dao.Semester;
import org.nauka.model.dao.SemesterDirection;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class StudentDto {
    private String name;
    private Long indexNumber;
    private String e_mail;
    private List<Semester> semesters;
    private List<PaymentDto> paymentsList;
    private List<SemesterDirection> semesterDirectionList;

    public static StudentDto of(String name, Long indexNumber, String e_mail, List<Semester> semesters, List<SemesterDirection> semesterDirections) {
        StudentDto studentDto = new StudentDto();
        studentDto.name = name;
        studentDto.indexNumber = indexNumber;
        studentDto.e_mail = e_mail;
        studentDto.semesters = semesters;
        studentDto.paymentsList = null;
        studentDto.semesterDirectionList = semesterDirections;
        return studentDto;
    }
}
