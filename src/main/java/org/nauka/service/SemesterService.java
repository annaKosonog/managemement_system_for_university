package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.exception.handler.service.AppException;
import org.nauka.exception.handler.service.ErrorType;
import org.nauka.mapper.SemesterMapper;
import org.nauka.model.dao.Semester;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.SemesterDto;
import org.nauka.repository.SemesterRepository;
import org.nauka.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SemesterService {
    private final SemesterRepository semesterRepository;
    private final TuitionFeeService tuitionFeeService;
    private final SemesterMapper semesterMapper;
    private final StudentRepository studentRepository;


    @Transactional
    public SemesterDto createNewSemester(Semester semester) {
        Semester newSemester = new Semester(
                semester.getName(),
                semester.getStartDate(),
                semester.getEndDate()
        );
        semesterRepository.save(newSemester);
        return semesterMapper.toSemesterDto(newSemester);
    }

    public Semester getSemesterById(Long id) {
        Semester bySemester = semesterRepository.findSemesterBySemesterId(id);
        if (bySemester == null) {
            throw new AppException("Semester", id, ErrorType.NOT_FOUND);
        }
        return bySemester;
    }

    public SemesterDto addStudentToSemester(Long idSemester, Long indexNumber) {
        Semester semester = getSemesterById(idSemester);

        Student student = studentRepository.findByIndexNumber(indexNumber).orElseThrow();

        if (hasSemester(student, idSemester)) {
            throw new AppException("Semester", idSemester, ErrorType.ALREADY_EXISTS);
        }

        if (semester.getStudent() == null) {
            semester.setStudent(new ArrayList<>());
        }
        studentRepository.save(student);
        semester.getStudent().add(student);
        semesterRepository.save(semester);
        return semesterMapper.toSemesterDto(semester);
    }

    private boolean hasSemester(Student student, Long idSemester) {
        if (student.getSemesterDirections() == null) {
            return false;
        }

        return student.getSemesterDirections().stream()
                .filter(dir -> dir.getSemester() != null)
                .flatMap(dir -> dir.getSemester().stream())
                .anyMatch(s -> s.getSemesterId().equals(idSemester));
    }
}
