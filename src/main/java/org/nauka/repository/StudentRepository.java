package org.nauka.repository;

import jakarta.transaction.Transactional;
import org.nauka.model.dao.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByIndexNumber(Long indexId);

    Optional<Student> findByIndexNumber(Long indexNumber);

    @Override
    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student")
    void clear();
}
