package org.nauka.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class PaymentDto {

    private StudentDto studentDto;

    private LocalDate paymentDate;

    private BigDecimal amount;

    private TuitionFeeDto tuitionFeeDto;


    public static PaymentDto of(StudentDto studentDto, LocalDate paymentDate, BigDecimal amount, TuitionFeeDto tuitionFeeDto) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.studentDto = studentDto;
        paymentDto.paymentDate = paymentDate;
        paymentDto.amount = amount;
        paymentDto.tuitionFeeDto = tuitionFeeDto;
        return paymentDto;

    }
}
