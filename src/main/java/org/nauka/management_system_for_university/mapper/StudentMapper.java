package org.nauka.management_system_for_university.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nauka.management_system_for_university.model.dao.Student;
import org.nauka.management_system_for_university.model.dto.StudentDto;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "studentId", ignore = true)
    Student toDao(StudentDto studentDto);
}
