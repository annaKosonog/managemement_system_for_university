package org.nauka.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class StudentDto {
    private String name;
    private Long indexNumber;
    private String e_mail;


}
