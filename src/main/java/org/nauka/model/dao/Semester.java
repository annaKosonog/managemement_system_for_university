package org.nauka.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Setter
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long semesterId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private SemesterDirection semesterDirection;

    @OneToMany
    private List<TuitionFee> tuitionFee;

    @ManyToOne
    private StudentDetails studentDetails;

    public Semester(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public static Semester of(String name, LocalDate startDate, LocalDate endDate, SemesterDirection semesterDirection, TuitionFee tuitionFee) {
        Semester newSemester = new Semester();
        newSemester.name = name;
        newSemester.startDate = startDate;
        newSemester.endDate = endDate;
        newSemester.semesterDirection = semesterDirection;
        newSemester.tuitionFee = List.of(tuitionFee);
        return newSemester;
    }
}
