package org.nauka.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStudent;
    private String name;
    private Long indexNumber;
    private String email;
    @OneToMany
    private List<Payment> paymentList;
    @OneToMany
    private List<SemesterDirection> semesterDirections;

}
