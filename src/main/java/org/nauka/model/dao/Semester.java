package org.nauka.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
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

    public Semester(Long semesterId, String name, LocalDate startDate, LocalDate endDate) {
        this.semesterId = semesterId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
