package org.nauka.model.dao;

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

    public static TuitionFee createTuition(Student student, Semester semester, BigDecimal amount,
                                           LocalDate dueDate, PaymentStatus status) {
        TuitionFee fee = new TuitionFee();
        fee.student = student;
        fee.semester = semester;
        fee.amount = amount;
        fee.paymentDueDate = dueDate;
        fee.paymentStatus = status;
        return fee;
    }
}
