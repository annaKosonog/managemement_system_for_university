package org.nauka.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class TuitionFee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tuitionFeeId;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Semester semester;

    @ManyToOne
    private SemesterDirection semesterDirection;

    private BigDecimal amount;

    private LocalDate paymentDueDate; //termin płatności


    @Enumerated(EnumType.STRING)
    @Setter
    private PaymentStatus paymentStatus;


    public static TuitionFee of(Student student, Semester idSemester, BigDecimal amount, PaymentStatus status) {
        TuitionFee fee = new TuitionFee();
        fee.student = student;
        fee.semester = idSemester;
        fee.amount = amount;
        fee.paymentStatus = status;
        return fee;
    }
}
