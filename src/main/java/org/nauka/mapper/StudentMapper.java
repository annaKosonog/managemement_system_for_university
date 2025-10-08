package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PaymentMapper.class, SemesterDirectionMapper.class})
public interface StudentMapper {

    @Mapping(source = "paymentsList", target = "paymentList")
    @Mapping(target = "idStudent", ignore = true)
    Student toEntity(StudentDto studentDto);

    @Mapping(source = "paymentList", target = "paymentsList")
    StudentDto toDto(Student student);

    List<StudentDto> toDtoList(List<Student> entities);
    List<Student> toEntityList(List<StudentDto> dtos);
}
