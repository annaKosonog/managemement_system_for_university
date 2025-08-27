package org.nauka.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SemesterDirection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSemesterDirection;

    private String direction;

    @OneToMany(mappedBy = "semesterDirection")
    private List<Semester> degree;

    @OneToMany
    private List<TuitionFee> tuitionFee;
}
