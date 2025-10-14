package org.nauka.repository;

import jakarta.transaction.Transactional;
import org.nauka.model.dao.SemesterDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterDirectionRepository extends JpaRepository<SemesterDirection, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Semester")
    void clear();

}
