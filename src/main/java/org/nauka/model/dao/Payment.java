package org.nauka.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;
    @ManyToOne
    private Student student;

    private LocalDate paymentDate;

    private BigDecimal amount;

    @ManyToOne
    private TuitionFee tuitionFee;

}
