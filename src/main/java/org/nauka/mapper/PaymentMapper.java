package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nauka.model.dao.Payment;
import org.nauka.model.dto.PaymentDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EntityIdMapper.class})
public interface PaymentMapper {

    @Mapping(target = "paymentId", ignore = true)
    @Mapping(source = "studentId", target = "student")
    @Mapping(source = "tuitionFeeId", target = "tuitionFee")
    Payment toEntity(PaymentDto paymentDto);


    @Mapping(source = "student", target = "studentId")
    @Mapping(source = "tuitionFee", target = "tuitionFeeId")
    PaymentDto toDto(Payment payment);

    @Mapping(source = "student", target = "studentId")
    @Mapping(source = "tuitionFee", target = "tuitionFeeId")
    List<PaymentDto> toDtoList(List<Payment> entities);

    List<Payment> toEntityList(List<PaymentDto> dtos);
}
