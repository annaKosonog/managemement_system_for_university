package org.nauka.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.mapper.StudentMapper;
import org.nauka.mapper.TuitionFeeMapper;
import org.nauka.model.SemesterTest;
import org.nauka.model.dao.Semester;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.StudentDto;
import org.nauka.model.dto.TuitionFeeDto;
import org.nauka.repository.StudentRepository;
import org.nauka.repository.TuitionFeeRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.nauka.model.student.StudentDaoTestData.adamKowalskiWithId;
import static org.nauka.model.student.StudentDtoTestData.adamKowalskiDto;
import static org.nauka.model.tuitionFee.TuitionFeeDtoTest.tuitionFeePaidDto;
import static org.nauka.model.tuitionFee.TuitionFeeDtoTest.tuitionFeePendingDto;

@ExtendWith(MockitoExtension.class)
class TuitionFeeServiceTest implements SemesterTest {

    @Mock
    TuitionFeeRepository tuitionFeeRepository;

    @Mock
    StudentRepository studentRepository;

    @Mock
    StudentMapper studentMapper;

    @Mock
    TuitionFeeMapper tuitionFeeMapper;

    @InjectMocks
    TuitionFeeService tuitionFeeService;

    @Test
    void shouldAddTuitionFeeToSemester() {
        StudentDto studentDto = adamKowalskiDto();

        doNothing().when(tuitionFeeRepository).save(any(TuitionFee.class));

        when(tuitionFeeMapper.toDtoTuitionFeeDto(any())).thenReturn(tuitionFeePaidDto(), tuitionFeePendingDto());

        List<TuitionFeeDto> result = tuitionFeeService.assignTuitionToSemesters(studentDto, List.of(semesterSummer, semesterWinter), new BigDecimal("2900"));

        assertEquals(2, result.size());
        assertTrue(result.contains(tuitionFeePaidDto()));
        assertTrue(result.contains(tuitionFeePendingDto()));

        verify(tuitionFeeRepository, times(2)).save(any(TuitionFee.class));
        verify(tuitionFeeMapper, times(2)).toDtoTuitionFeeDto(any());
    }

    //Jeśli amount == null powinno rzucić wyjątek
    @Test
    void shouldThrowExceptionWhenAmountIsNull() {
        StudentDto studentDto = adamKowalskiDto();
        List<Semester> semesters = List.of(semesterSummer);

        assertThrows(IllegalArgumentException.class, () ->
                tuitionFeeService.assignTuitionToSemesters(studentDto, semesters, null)
        );
    }

    @Test
    void shouldThrowExceptionWhenAmountIsNegative() {
        StudentDto studentDto = adamKowalskiDto();
        List<Semester> semesters = List.of(semesterSummer);
        assertThrows(IllegalArgumentException.class, () ->
                tuitionFeeService.assignTuitionToSemesters(studentDto, semesters, new BigDecimal("-1000"))
        );
    }

    @Test
    void shouldReturnEmptyListWhenNoSemestersProvided() {
        StudentDto studentDto = adamKowalskiDto();

        // when
        List<TuitionFeeDto> result = tuitionFeeService.assignTuitionToSemesters(studentDto, List.of(), new BigDecimal("2500"));

        // then
        assertTrue(result.isEmpty());
        verify(tuitionFeeRepository, never()).save(any());
    }
}