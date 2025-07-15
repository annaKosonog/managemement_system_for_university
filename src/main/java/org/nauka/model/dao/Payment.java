package org.nauka.model.dao;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Payment {
    private Long paymentId;
    private Student student;

    private LocalDate paymentDate;

    private BigDecimal amount;

    private TuitionFee tuitionFee;

}
