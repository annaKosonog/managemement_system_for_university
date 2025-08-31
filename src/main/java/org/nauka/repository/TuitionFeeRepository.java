package org.nauka.repository;

import org.nauka.model.dao.TuitionFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TuitionFeeRepository extends JpaRepository<TuitionFee, Long> {
    void clear();
}
