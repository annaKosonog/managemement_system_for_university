package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "studentId", ignore = true)
    Student toDao(StudentDto studentDto);

    StudentDto toDto(Student student);
}
