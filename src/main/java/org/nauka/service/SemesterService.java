package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.exception.handler.service.AppException;
import org.nauka.exception.handler.service.ErrorType;
import org.nauka.mapper.StudentMapper;
import org.nauka.model.dao.Semester;
import org.nauka.repository.SemesterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SemesterService {
    private final SemesterRepository semesterRepository;
    private final TuitionFeeService tuitionFeeService;
    private final StudentService studentService;
    private final StudentMapper studentMapper;


    @Transactional
    public Semester createNewSemester(Semester semester) {
        Semester newSemester = new Semester(
                semester.getName(),
                semester.getStartDate(),
                semester.getEndDate()
        );
        semesterRepository.save(newSemester);
        return newSemester;
    }

    public Semester getSemesterById(Long id) {
        Semester bySemester = semesterRepository.findSemesterBySemesterId(id);
        if (bySemester == null) {
            throw new AppException("Semester", id, ErrorType.NOT_FOUND);
        }
        return bySemester;
    }

   /* public Semester addStudentToSemester(Long idSemester) {
        List<Student> students = studentService.getStudents()
                .stream()
                .map(studentMapper::toStudentDao)
                .filter(student -> hasSemester(student, idSemester))
                .collect(Collectors.toList());
    }

    private boolean hasSemester(Student student, Long idSemester) {
        if (student.getSemesters() == null) return false;
        return student.getSemesters().stream()
                .anyMatch(s -> s.getId().equals(idSemester));
    }*/

   /* public SemesterDirection addNewSemesterDirectionToSemester(Long idSemesterDirection){

    }*/

}
