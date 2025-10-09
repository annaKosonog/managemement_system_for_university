package org.nauka.model.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class SemesterDirectionDto {
    private Long idSemesterDirection;
    private String direction;
    private List<Long> semesterIds;
    private List<Long> tuitionFeeIds;
}
