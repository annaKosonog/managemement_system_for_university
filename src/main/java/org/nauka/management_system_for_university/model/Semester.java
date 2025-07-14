package org.nauka.management_system_for_university.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Semester {
    private Long semesterId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
