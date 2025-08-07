package org.nauka.repository;

import org.nauka.model.dao.Semester;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository {
    Semester findIdBySemester(Long idSemester);

    void clear();
}
