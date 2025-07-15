package org.nauka.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@RequiredArgsConstructor
public class StudentDto {
    final private Long studentId;
    final private String name;
    final private Long indexNumber;
    final private String e_mail;
}
