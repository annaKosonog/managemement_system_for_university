package org.nauka.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
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
}
