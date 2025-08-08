package org.nauka.model.dao;

import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
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
