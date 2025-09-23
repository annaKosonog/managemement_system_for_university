package org.nauka.repository;

import jakarta.transaction.Transactional;
import org.nauka.model.dao.TuitionFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TuitionFeeRepository extends JpaRepository<TuitionFee, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM TuitionFee")
    void clear();
}
