package org.nauka.model.dao;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Student {
    @Setter
    private Long idStudent;
    private String name;
    private Long indexNumber;
    private String email;
    private List<Semester> semesters;

}
