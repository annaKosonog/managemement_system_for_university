package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.model.dao.Semester;
import org.nauka.repository.SemesterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SemesterService {
    private final SemesterRepository semesterRepository;

    public Semester getSemesterById(Long id) {
        Semester bySemester = semesterRepository.findIdBySemester(id);
        if (bySemester == null) {
            throw new IllegalArgumentException("Not found semester by id: " + id);
        }
        return bySemester;
    }

}
