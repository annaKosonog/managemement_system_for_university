package org.nauka.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.nauka.exception.handler.service.AppException;
import org.nauka.exception.handler.service.ErrorType;
import org.nauka.mapper.SemesterDirectionMapper;
import org.nauka.model.dao.Semester;
import org.nauka.model.dao.SemesterDirection;
import org.nauka.model.dto.SemesterDirectionDto;
import org.nauka.repository.SemesterDirectionRepository;
import org.nauka.repository.SemesterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterDirectionService {

    private final SemesterDirectionRepository semesterDirectionRepository;
    private final SemesterDirectionMapper semesterDirectionMapper;
    private final SemesterRepository semesterRepository;

    @Transactional
    public SemesterDirectionDto createNewSemesterDirection(String name) {
        SemesterDirection direction = new SemesterDirection(
                name
        );
        semesterDirectionRepository.save(direction);

        return semesterDirectionMapper.toDto(direction);
    }

    public SemesterDirection getSemesterDirectionById(Long idSemesterDirection) {
        return semesterDirectionRepository.findById(idSemesterDirection)
                .orElseThrow(() -> new AppException("Semester_direction", idSemesterDirection, ErrorType.NOT_FOUND));
    }

    @Transactional
    public SemesterDirectionDto addSemesterDirectionToSemester(Long idSemesterDirection, Long idSemester) {
        SemesterDirection direction = getSemesterDirectionById(idSemesterDirection);
        Semester semester = semesterRepository.findSemesterBySemesterId(idSemester);

        // Dodaj semestr do listy semestrów w danym kierunku
        List<Semester> semesters;

        if (direction.getSemester() == null) {
            semesters = new ArrayList<>();
        } else {
            semesters = new ArrayList<>(direction.getSemester());
        }

        // Ustaw relację dwustronną
        semesters.add(semester);
        semester.setSemesterDirection(direction);

        // Zapisz zmiany (zależnie od tego, gdzie masz logikę zapisu)
        semesterRepository.save(semester);
        semesterDirectionRepository.save(direction);
        System.out.println(direction);

        // Zwróć DTO
        return semesterDirectionMapper.toDto(direction);
    }
}
