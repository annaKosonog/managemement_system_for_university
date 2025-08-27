package org.nauka.repository;

import org.nauka.model.dao.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    BigDecimal sumPaymentsByTuitionFee(Long idPayment);
}
