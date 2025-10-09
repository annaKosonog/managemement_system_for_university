package org.nauka.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class SemesterDto {

    private Long semesterId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Long> studentIds;
}
