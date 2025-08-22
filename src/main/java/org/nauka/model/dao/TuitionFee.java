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

    @Setter
    private Student student;

    private Semester semester;

    private BigDecimal amount;

    private LocalDate paymentDueDate; //termin płatności

    @Setter
    private PaymentStatus paymentStatus;


   public static TuitionFee of(Long idStudent, Semester idSemester, BigDecimal amount, PaymentStatus status){
       Student newStudent = new Student();
       newStudent.setIdStudent(idStudent);

       TuitionFee fee = new TuitionFee();
       fee.student = newStudent;
       fee.semester = idSemester;
       fee.amount = amount;
       fee.paymentStatus = status;
       return fee;
   }
}
