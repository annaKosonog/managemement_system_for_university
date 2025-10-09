package org.nauka.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PaymentDto {

    private Long studentId;

    private LocalDate paymentDate;

    private BigDecimal amount;

    private Long tuitionFeeId;


    public static PaymentDto of(Long studentId, LocalDate paymentDate, BigDecimal amount, Long tuitionFeeId) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.studentId = studentId;
        paymentDto.paymentDate = paymentDate;
        paymentDto.amount = amount;
        paymentDto.tuitionFeeId = tuitionFeeId;
        return paymentDto;

    }
}
