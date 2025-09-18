package org.nauka.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.exception.semester.SemesterNotFoundException;
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

    @InjectMocks
    SemesterService semesterService;

    @Test
    void shouldReturnSemesterWhenSemesterExists() {
        Long idSemester = 1L;
        when(semesterRepository.findIdBySemester(idSemester)).thenReturn(SemesterTest.semesterSummer);

        Semester result = semesterService.getSemesterById(idSemester);

        assertNotNull(result);
        assertEquals(idSemester, result.getSemesterId());

        verify(semesterRepository).findIdBySemester(idSemester);
    }

    @Test
    void shouldThrowExceptionWhenSemesterDoesNotExist() {
        Long id = 99L;
        when(semesterRepository.findIdBySemester(id)).thenReturn(null);

        SemesterNotFoundException exception = assertThrows(
                SemesterNotFoundException.class,
                () -> semesterService.getSemesterById(id)
        );
        assertTrue(exception.getMessage().contains("Not found semester by id: " + id));
        verify(semesterRepository).findIdBySemester(id);
    }
}
