package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.exception.semester.SemesterNotFoundException;
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
            throw new SemesterNotFoundException(id);
        }
        return bySemester;
    }

}
