package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.nauka.model.dao.SemesterDirection;
import org.nauka.model.dto.SemesterDirectionDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EntityIdMapper.class})
public interface SemesterDirectionMapper {

    @Mappings({
            @Mapping(source = "semesterIds", target = "semester"),
            @Mapping(source = "tuitionFeeIds", target = "tuitionFee"),
            @Mapping(target = "student", ignore = true),
    })
    SemesterDirection toEntity(SemesterDirectionDto dto);

    @Mappings({
            @Mapping(source = "semester", target = "semesterIds"),
            @Mapping(source = "tuitionFee", target = "tuitionFeeIds")
    })
    SemesterDirectionDto toDto(SemesterDirection entity);

    List<Long> toDtoList(List<SemesterDirection> entities);
}
