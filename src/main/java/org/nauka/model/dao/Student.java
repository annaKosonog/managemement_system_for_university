package org.nauka.model.dao;

import lombok.*;


@Getter
@EqualsAndHashCode
@ToString
public class Student {
    @Setter
    private Long studentId;
    private String name;
    private Long indexNumber;
    private String e_mail;

    public Student(String name, Long indexNumber, String e_mail) {
        this.name = name;
        this.indexNumber = indexNumber;
        this.e_mail = e_mail;
    }

    public Student(Long studentId, String name, Long indexNumber, String e_mail) {
        this.studentId = studentId;
        this.name = name;
        this.indexNumber = indexNumber;
        this.e_mail = e_mail;
    }
}
