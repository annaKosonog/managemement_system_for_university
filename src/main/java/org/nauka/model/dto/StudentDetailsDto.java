package org.nauka.model.dto;

import lombok.*;
import org.nauka.model.dao.Semester;
import org.nauka.model.dao.SemesterDirection;
import org.nauka.model.dao.Student;
import org.nauka.model.dao.TuitionFee;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class StudentDetailsDto {
    private Long idStudentDetails;
    private Student student;
    private List<Semester> semester;
    private List<SemesterDirection> semesterDirection;
    private List<TuitionFee> tuitionFee;
}
