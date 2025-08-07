package org.nauka.model.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@EqualsAndHashCode
@ToString
@Setter
public class Semester {
    private Long semesterId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    public Semester(Long semesterId, String name, LocalDate startDate, LocalDate endDate) {
        this.semesterId = semesterId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
