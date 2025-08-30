package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "studentId", source = "id")
    Student toStudentDao(StudentDto studentDto);

    StudentDto toStudentDto(Student student);
}
