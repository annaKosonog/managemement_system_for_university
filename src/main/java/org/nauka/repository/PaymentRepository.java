package org.nauka.repository;

import org.nauka.model.dao.Payment;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PaymentRepository {
    void savePayment(Payment payment);

    Long generateId();

    BigDecimal sumPaymentsByTuitionFee(Long idTuition);
}
