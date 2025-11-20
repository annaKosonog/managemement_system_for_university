package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EntityIdMapper.class})
public interface StudentMapper {

    @Mapping(source = "paymentIds", target = "paymentList")
    @Mapping(target = "idStudent", ignore = true)
    Student toEntity(StudentDto studentDto);


    StudentDto toDto(Student student);

    List<StudentDto> toDtoList(List<Student> entities);

    List<Student> toEntityList(List<StudentDto> dtos);
}
