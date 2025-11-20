package org.nauka.model.dto;

import lombok.*;
import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dao.StudentDetails;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class TuitionFeeDto {

    private Long tuitionFeeId;
    private Long studentId;
    private Long semesterId;
    private Long semesterDirectionId;
    private StudentDetails studentDetails;
    private BigDecimal amount;

    private LocalDate paymentDueDate;

    private PaymentStatus paymentStatus;

}
