package org.nauka.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class SemesterDirection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSemesterDirection;

    private String direction;

    @OneToMany(mappedBy = "semesterDirection")
    private List<Semester> semester;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Setter
    @OneToMany
    private List<TuitionFee> tuitionFee;


    public SemesterDirection(String direction) {
        this.direction = direction;
    }

    public SemesterDirection(Long idSemesterDirection, String direction, List<Semester> semesters, Student student, List<TuitionFee> tuitionFees) {
        this.idSemesterDirection = idSemesterDirection;
        this.direction = direction;
        this.semester = semesters; // albo jak nazywa się Twoje pole
        this.student = student;
        this.tuitionFee = tuitionFees;
    }

}
