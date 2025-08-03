package org.nauka.model.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dao.Semester;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TuitionFeeDto {
    private StudentDto studentDto;

    private Semester semester;

    private BigDecimal amount;

    private LocalDate paymentDueDate;

    private PaymentStatus paymentStatus;

}
