package org.nauka.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.exception.handler.service.AppException;
import org.nauka.mapper.SemesterMapper;
import org.nauka.model.dao.Semester;
import org.nauka.model.dto.SemesterDto;
import org.nauka.repository.SemesterRepository;
import org.nauka.repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.nauka.model.SemesterTest.*;

@ExtendWith(MockitoExtension.class)
public class SemesterServiceTest {
    @Mock
    SemesterRepository semesterRepository;

    @Mock
    TuitionFeeService tuitionFeeService;

    @Mock
    SemesterMapper semesterMapper;

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    SemesterService semesterService;

    @Test
    void shouldReturnSemesterWhenSemesterExists() {
        Long idSemester = 1L;
        when(semesterRepository.findSemesterBySemesterId(idSemester)).thenReturn(semesterSummer);

        Semester result = semesterService.getSemesterById(idSemester);

        assertNotNull(result);
        assertEquals("letni", result.getName());

        verify(semesterRepository).findSemesterBySemesterId(idSemester);
    }

    @Test
    void shouldThrowExceptionWhenSemesterDoesNotExist() {
        Long id = 99L;
        when(semesterRepository.findSemesterBySemesterId(id)).thenReturn(null);

        AppException exception = assertThrows(
                AppException.class,
                () -> semesterService.getSemesterById(id)
        );
        assertEquals(STR."Semester with id \{id} not found", exception.getMessage());
        verify(semesterRepository).findSemesterBySemesterId(id);
    }

    @Test
    void shouldReturnNewSemester() {
        Semester semester = semesterWinter;

        when(semesterRepository.save(semester)).thenReturn(semester);
        when(semesterMapper.toSemesterDto(semester)).thenReturn(semesterSummerDto);
        SemesterDto newSemester = semesterService.createNewSemester(semester);
        verify(semesterRepository).save(semester);
        assertEquals("letni", newSemester.getName());
    }
}
