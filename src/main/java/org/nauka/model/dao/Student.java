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
    private Long studentId;
    private String name;
    private Long indexNumber;
    private String e_mail;
    private List<Semester> semesters;

}
