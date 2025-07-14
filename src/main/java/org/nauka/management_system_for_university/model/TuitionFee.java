package org.nauka.management_system_for_university.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class TuitionFee {
    private Long tuitionFeeId;
    private Student student;

    private Semester semester;

    private BigDecimal amount;

    private LocalDate paymentDueDate;

    private PaymentStatus paymentStatus;
}
