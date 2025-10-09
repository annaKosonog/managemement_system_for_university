package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.exception.handler.service.AppException;
import org.nauka.exception.handler.service.ErrorType;
import org.nauka.model.dao.SemesterDirection;
import org.nauka.repository.SemesterDirectionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SemesterDirectionService {

    private final SemesterDirectionRepository semesterDirectionRepository;

    public SemesterDirection createNewSemesterDirection(String name) {
        SemesterDirection direction = new SemesterDirection(
                name
        );
        semesterDirectionRepository.save(direction);
        return direction;
    }

    public SemesterDirection getSemesterDirectionById(Long idSemesterDirection) {
        return semesterDirectionRepository.findById(idSemesterDirection)
                .orElseThrow(() -> new AppException("Semester_direction", idSemesterDirection, ErrorType.NOT_FOUND));
    }
}
