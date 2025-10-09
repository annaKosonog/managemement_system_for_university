package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nauka.model.dao.SemesterDirection;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {EntityIdMapper.class})
public interface StudentMapper {

    @Mapping(source = "paymentIds", target = "paymentList")
    @Mapping(target = "idStudent", ignore = true)
    Student toEntity(StudentDto studentDto);

    @Mapping(source = "paymentList", target = "paymentIds")
    @Mapping(source = "semesterDirections", target = "semesterDirectionIds")
    StudentDto toDto(Student student);

    List<StudentDto> toDtoList(List<Student> entities);

    List<Student> toEntityList(List<StudentDto> dtos);
}
