package org.nauka.management_system_for_university.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Student {
    private Long studentId;
    private String name;
    private Long indexNumber;
    private String e_mail;

}
