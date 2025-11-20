package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.TuitionFeeDto;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {StudentMapper.class, EntityIdMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TuitionFeeMapper {

    TuitionFee toEntity(TuitionFeeDto tuitionFeeDto);

    @Mapping(source = "student", target = "studentId")
    TuitionFeeDto toDto(TuitionFee tuitionFee);

    List<TuitionFeeDto> toDtoList(List<TuitionFee> entities);

    List<TuitionFee> toEntityList(List<TuitionFeeDto> dtos);
}
