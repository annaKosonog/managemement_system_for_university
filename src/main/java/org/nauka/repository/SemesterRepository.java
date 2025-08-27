package org.nauka.repository;

import org.nauka.model.dao.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    Semester findIdBySemester(Long idSemester);

    void clear();
}
