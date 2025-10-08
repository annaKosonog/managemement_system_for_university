package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.nauka.model.dao.SemesterDirection;
import org.nauka.model.dto.SemesterDirectionDto;
import org.nauka.model.dto.SemesterDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SemesterDirectionMapper {

    @Mappings({
            @Mapping(source = "semesters", target = "semester"),
            @Mapping(source = "tuitionFee", target = "tuitionFee"),
            @Mapping(target = "student", ignore = true),
            @Mapping(target = "idSemesterDirection", ignore = true)
    })
    SemesterDirection toEntity(SemesterDirectionDto dto);
    @Mappings({
            @Mapping(source = "semester", target = "semesters"),
            @Mapping(source = "tuitionFee", target = "tuitionFee")
    })
    SemesterDirectionDto toDto(SemesterDirection entity);

    List<SemesterDirectionDto> toDtoList(List<SemesterDirection> entities);

    List<SemesterDirection> toEntityList(List<SemesterDirectionDto> dtos);
}
