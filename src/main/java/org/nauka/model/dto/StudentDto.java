package org.nauka.model.dto;

import lombok.*;


@EqualsAndHashCode
@ToString
public class StudentDto {
    private String name;
    private Long indexNumber;
    private String e_mail;

    public StudentDto(String name, Long indexNumber, String e_mail) {
        this.name = name;
        this.indexNumber = indexNumber;
        this.e_mail = e_mail;
    }

    public String getName() {
        return name;
    }

    public Long getIndexNumber() {
        return indexNumber;
    }

    public String getE_mail() {
        return e_mail;
    }
}
