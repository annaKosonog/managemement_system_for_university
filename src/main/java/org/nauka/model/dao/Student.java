package org.nauka.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStudent;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "index_number", nullable = false, unique = true)
    private Long indexNumber;
    @Column(name = "email", nullable = false)
    private String email;
    @OneToMany
    private List<Payment> paymentList;
    @OneToMany
    private List<SemesterDirection> semesterDirections;

}
