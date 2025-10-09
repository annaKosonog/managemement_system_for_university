package org.nauka.repository;

import org.nauka.model.dao.SemesterDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterDirectionRepository extends JpaRepository<SemesterDirection, Long> {


}
