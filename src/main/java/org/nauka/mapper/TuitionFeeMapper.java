package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.TuitionFeeDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface TuitionFeeMapper {

    @Mapping(source = "studentDto", target = "student")
    @Mapping(target = "tuitionFeeId", ignore = true)
    TuitionFee toEntity(TuitionFeeDto tuitionFeeDto);

    @Mapping(source = "student", target = "studentDto")
    TuitionFeeDto toDto(TuitionFee tuitionFee);

    List<TuitionFeeDto> toDtoList(List<TuitionFee> entities);

    List<TuitionFee> toEntityList(List<TuitionFeeDto> dtos);
}
