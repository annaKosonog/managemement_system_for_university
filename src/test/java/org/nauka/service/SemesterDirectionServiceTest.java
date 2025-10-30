package org.nauka.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.exception.handler.service.AppException;
import org.nauka.mapper.SemesterDirectionMapper;
import org.nauka.model.dao.SemesterDirection;
import org.nauka.model.dto.SemesterDirectionDto;
import org.nauka.repository.SemesterDirectionRepository;
import org.nauka.repository.SemesterRepository;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.nauka.model.SemesterTest.semesterWinterDao;
import static org.nauka.model.semesterDirection.SemesterDirectionDaoTest.administrationWithId;

@ExtendWith(MockitoExtension.class)
public class SemesterDirectionServiceTest {

    @Mock
    SemesterDirectionRepository semesterDirectionRepository;
    @Mock
    SemesterDirectionMapper semesterDirectionMapper;
    @Mock
    SemesterRepository semesterRepository;

    @InjectMocks
    SemesterDirectionService semesterDirectionService;

    @AfterEach
    void tearDown() {
        semesterDirectionRepository.clear();
    }

    @Test
    void shouldSavedNewSemesterDirection() {
        String name = "ADMINISTRATION";

        semesterDirectionService.createNewSemesterDirection(name);
        verify(semesterDirectionRepository).save(any());
    }

    @Test
    void shouldReturnNewSemesterDirection() {
        String name = "ADMINISTRATION";
        SemesterDirectionDto dto = new SemesterDirectionDto();
        dto.setDirection(name);

        when(semesterDirectionMapper.toDto(any(SemesterDirection.class))).thenReturn(dto);

        SemesterDirectionDto result = semesterDirectionService.createNewSemesterDirection(name);
        assertEquals(name, result.getDirection());
    }

    @Test
    void shouldReturnSemesterDirectionById() {
        Long id = 1L;

        when(semesterDirectionRepository.findById(id)).thenReturn(Optional.of(administrationWithId()));

        SemesterDirection semesterDirection = semesterDirectionService.getSemesterDirectionById(id);

        verify(semesterDirectionRepository).findById(id);
        assertEquals(id, semesterDirection.getIdSemesterDirection());
    }

    @Test
    void shouldThrowExceptionWhenDoNotFindSemesterDirectionById() {
        Long id = 1L;
        when(semesterDirectionRepository.findById(id)).thenReturn(Optional.empty());

        AppException exception = assertThrows(AppException.class, () -> semesterDirectionService.getSemesterDirectionById(id));

        assertThat(Objects.equals(exception.getMessage(), STR."Student with id \{id} not found"));
    }

    @Test
    void shouldAddNewSemesterToSemesterDirection() {
        Long idSemesterDirection = 1L;
        Long idSemester = 2L;
        //   administrationWithId().setSemester(null);

        when(semesterDirectionRepository.findById(idSemesterDirection)).thenReturn(Optional.of(administrationWithId()));
        when(semesterRepository.findSemesterBySemesterId(idSemester)).thenReturn(semesterWinterDao);

        semesterDirectionService.addSemesterDirectionToSemester(idSemesterDirection, idSemester);

        verify(semesterDirectionRepository).save(any(SemesterDirection.class));
        verify(semesterRepository).save(any());

        assertNotNull(administrationWithId().getSemester());
        assertEquals(1, administrationWithId().getSemester().size());
        //  assertTrue(administrationWithId().getSemester().contains(semesterWinterDao));
        assertEquals(administrationWithId(), semesterWinterDao.getSemesterDirection());

    }
}
