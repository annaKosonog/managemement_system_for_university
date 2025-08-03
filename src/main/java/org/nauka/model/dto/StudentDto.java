package org.nauka.model.dto;

import lombok.*;
import org.nauka.model.dao.Semester;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class StudentDto {
    private String name;
    private Long indexNumber;
    private String e_mail;
    private List<Semester> semesters;
}
