package org.nauka.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class StudentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStudentDetails;
    @OneToOne
    private Student student;
    @OneToMany
    private List<Semester> semester;
    @OneToMany
    private List<SemesterDirection> semesterDirection;
    @OneToMany
    private List<TuitionFee> tuitionFee;
}
