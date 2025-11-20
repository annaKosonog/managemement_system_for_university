package org.nauka.model.dao;

import jakarta.persistence.*;
import lombok.*;

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

    @Setter
    @OneToMany
    private List<TuitionFee> tuitionFee;


    public SemesterDirection(String direction) {
        this.direction = direction;
    }

    public SemesterDirection(Long idSemesterDirection, String direction, List<Semester> semesters, List<TuitionFee> tuitionFees) {
        this.idSemesterDirection = idSemesterDirection;
        this.direction = direction;
        this.semester = semesters; // albo jak nazywa się Twoje pole
        this.tuitionFee = tuitionFees;
    }

}
