package org.nauka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.nauka.model.dao.Payment;
import org.nauka.model.dto.PaymentDto;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "paymentId", ignore = true)
    Payment toPaymentDao(PaymentDto paymentDto);

    PaymentDto toPaymentDto(Payment payment);
}
