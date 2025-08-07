package org.nauka.repository;

import org.nauka.model.dao.Semester;

import java.util.ArrayList;
import java.util.List;

public class SemesterRepositoryImpl implements SemesterRepository {

    private final List<Semester> semesters = new ArrayList<>();

    @Override
    public Semester findIdBySemester(Long idSemester) {
        return semesters.stream()
                .filter(semester -> semester.getSemesterId().equals(idSemester))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void clear() {
        semesters.clear();
    }
}
