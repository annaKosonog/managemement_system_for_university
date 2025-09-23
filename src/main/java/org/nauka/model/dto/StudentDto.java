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
@Setter
public class StudentDto {
    private String name;
    private Long indexNumber;
    private String email;
 //   private List<Semester> semesters;
    private List<PaymentDto> paymentsList;
    private List<SemesterDirection> semesterDirectionList;

    public static StudentDto of(String name, Long indexNumber, String e_mail, List<SemesterDirection> semesterDirections) {
        StudentDto studentDto = new StudentDto();
        studentDto.name = name;
        studentDto.indexNumber = indexNumber;
        studentDto.email = e_mail;
     //   studentDto.semesters = semesters;
        studentDto.paymentsList = null;
        studentDto.semesterDirectionList = semesterDirections;
        return studentDto;
    }
}
