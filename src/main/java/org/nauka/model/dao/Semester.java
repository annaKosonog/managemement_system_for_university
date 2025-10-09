package org.nauka.model.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToMany
    private List<Student> student;

    @ManyToOne
    private SemesterDirection semesterDirection;

    @OneToMany
    private List<TuitionFee> tuitionFee;

    public Semester(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public static Semester of(String name, LocalDate startDate, LocalDate endDate, List<Student> student, SemesterDirection semesterDirection, TuitionFee tuitionFee) {
        Semester newSemester = new Semester();
        newSemester.name = name;
        newSemester.startDate = startDate;
        newSemester.endDate = endDate;
        newSemester.student = student;
        newSemester.semesterDirection = semesterDirection;
        newSemester.tuitionFee = List.of(tuitionFee);
        return newSemester;
    }
}
