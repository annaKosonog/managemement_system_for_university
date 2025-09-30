package org.nauka.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.exception.handler.service.AppException;
import org.nauka.model.SemesterTest;
import org.nauka.model.dao.Semester;
import org.nauka.repository.SemesterRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SemesterServiceTest {
    @Mock
    SemesterRepository semesterRepository;

    @Mock
    TuitionFeeService tuitionFeeService;


    @InjectMocks
    SemesterService semesterService;

    @Test
    void shouldReturnSemesterWhenSemesterExists() {
        Long idSemester = 1L;
        when(semesterRepository.findSemesterBySemesterId(idSemester)).thenReturn(SemesterTest.semesterSummer);

        Semester result = semesterService.getSemesterById(idSemester);

        assertNotNull(result);
        assertEquals(idSemester, result.getSemesterId());

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
        assertTrue(exception.getMessage().contains(STR."Not found semester by id: \{id}"));
        verify(semesterRepository).findSemesterBySemesterId(id);
    }
}
