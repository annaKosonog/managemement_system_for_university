package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.exception.handler.service.AppException;
import org.nauka.exception.handler.service.ErrorType;
import org.nauka.model.dao.Semester;
import org.nauka.repository.SemesterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SemesterService {
    private final SemesterRepository semesterRepository;

    public Semester getSemesterById(Long id) {
        Semester bySemester = semesterRepository.findSemesterBySemesterId(id);
        if (bySemester == null) {
            throw new AppException("Semester", id, ErrorType.NOT_FOUND);
        }
        return bySemester;
    }

}
