package org.nauka.service;

import org.nauka.model.dao.Semester;
import org.nauka.repository.SemesterRepository;
import org.springframework.stereotype.Service;

@Service
public class SemesterService {
    private SemesterRepository semesterRepository;

    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    public Semester getSemesterById(Long id) {
        Semester bySemester = semesterRepository.findIdBySemester(id);
        if (bySemester == null) {
            throw new IllegalArgumentException("Not found semester by id: " + id);
        }
        return bySemester;
    }

}
