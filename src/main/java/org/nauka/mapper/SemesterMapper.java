package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.nauka.model.dao.Semester;
import org.nauka.model.dto.SemesterDto;

@Mapper(componentModel = "spring", uses = {EntityIdMapper.class})
public interface SemesterMapper {

    SemesterDto toSemesterDto(Semester semester);
}
