package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.nauka.model.dao.Semester;
import org.nauka.model.dto.SemesterDto;

@Mapper(componentModel = "spring")
public interface SemesterMapper {


    SemesterDto toSemesterDto(Semester semester);
}
