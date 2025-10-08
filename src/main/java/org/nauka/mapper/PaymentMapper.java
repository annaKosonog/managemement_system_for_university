package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nauka.model.dao.Payment;
import org.nauka.model.dto.PaymentDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, TuitionFeeMapper.class})
public interface PaymentMapper {

    @Mapping(target = "paymentId", ignore = true)
    @Mapping(source = "studentDto", target = "student")
    @Mapping(source = "tuitionFeeDto", target = "tuitionFee")
    Payment toEntity(PaymentDto paymentDto);

    @Mapping(source = "student", target = "studentDto")
    @Mapping(source = "tuitionFee", target = "tuitionFeeDto")
    PaymentDto toDto(Payment payment);

    List<PaymentDto> toDtoList(List<Payment> entities);

    List<Payment> toEntityList(List<PaymentDto> dtos);
}
