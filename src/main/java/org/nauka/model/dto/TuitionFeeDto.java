package org.nauka.model.dto;

import lombok.*;
import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dao.Semester;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class TuitionFeeDto {
    private StudentDto studentDto;

    private Semester semester;

    private BigDecimal amount;

    private LocalDate paymentDueDate;

    private PaymentStatus paymentStatus;

}
