package org.nauka.model.payment;

import org.nauka.model.dao.Payment;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.nauka.model.student.StudentDaoTestData.adamKowalskiWithId;
import static org.nauka.model.student.StudentDaoTestData.alaKotWithId;
import static org.nauka.model.tuitionFee.TuitionFeeDaoTest.*;

public class PaymentDaoTest {
    public static Payment paidOnTime() {
        return new Payment(1L, adamKowalskiWithId(), LocalDate.of(2025, 2, 28), new BigDecimal("1000"), tuitionFeePaid());
    }

    public static Payment notPaidWithinFourteenDaysButWithoutInterest() {
        return new Payment(2L, alaKotWithId(), LocalDate.of(2025, 3, 10), new BigDecimal(1000), tuitionFeeNoPaid());
    }

    public static Payment paymentAfterTheFinalPaymentDateWithInterestCharged() {
        return new Payment(3L, alaKotWithId(), LocalDate.of(2025, 3, 17), new BigDecimal(1000), tuitionFeeOverdue());
    }

    public static Payment noPaidAfterTheFinalPaymentDate() {
        return new Payment(3L, alaKotWithId(), LocalDate.of(2025, 3, 29), new BigDecimal(1000), tuitionFeeNoPaid());
    }


}
