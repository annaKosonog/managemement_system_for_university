package org.nauka.repository;

import org.nauka.model.dao.TuitionFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TuitionFeeRepository extends JpaRepository<TuitionFee, Long> {

}
