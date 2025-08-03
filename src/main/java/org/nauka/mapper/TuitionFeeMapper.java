package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.TuitionFeeDto;

@Mapper(componentModel = "spring")
public interface TuitionFeeMapper {

    @Mapping(target = "tuitionFeeId", ignore = true)
    TuitionFee toDaoTuitionFee(TuitionFeeDto tuitionFeeDto);

    TuitionFeeDto toDtoTuitionFeeDto(TuitionFee tuitionFee);
}
