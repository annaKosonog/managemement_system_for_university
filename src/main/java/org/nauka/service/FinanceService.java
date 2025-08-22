package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.model.dao.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
public class FinanceService {

    @Value("${final.date.payment}")
    private int finalDatePayment;

    // Wyliczenie całkowitej kwoty do zapłaty,uwzględnia odsetki
    public BigDecimal calculateTotalAmount(Payment payment) {
        BigDecimal lateFee = calculateInterest(payment);
        return payment.getAmount().add(lateFee);
    }

    //Naliczanie odsetek w zależności od terminu wpłaty
    private BigDecimal calculateInterest(Payment payment) {
        if (isPaymentOnTime(payment)) {
            return BigDecimal.ZERO;
        } else if (isWithinExtendedDeadline(payment)) {
            long overdueDays = calculateDaysOverdue(payment);
            return new BigDecimal(2).multiply(BigDecimal.valueOf(overdueDays));
        } else {
            return BigDecimal.ZERO;
        }
    }

    private boolean isPaymentOnTime(Payment payment) {  //w terminie
        LocalDate deadLine = calculateStandardDeadline(payment);
        LocalDate paymentDate = payment.getPaymentDate();
        return !paymentDate.isAfter(deadLine);
    }

    private boolean isWithinExtendedDeadline(Payment payment) { //przedłużony termin
        LocalDate finaDeadline = calculateExtendedDeadline(payment);
        LocalDate paymentDate = payment.getPaymentDate();
        return !paymentDate.isAfter(finaDeadline);
    }

    private long calculateDaysOverdue(Payment payment) { //Liczba dni opóżnienia w płatności
        LocalDate paymentDate = payment.getPaymentDate();
        LocalDate standardDeadline = calculateStandardDeadline(payment);
        return ChronoUnit.DAYS.between(standardDeadline, paymentDate);
    }

    private LocalDate calculateStandardDeadline(Payment payment) {
        return payment.getTuitionFee().getSemester().getEndDate()
                .plusWeeks(2);
    }

    //Przedłużony termin obliczanie
    private LocalDate calculateExtendedDeadline(Payment payment) {
        return calculateStandardDeadline(payment)
                .plusDays(finalDatePayment);
    }
}
