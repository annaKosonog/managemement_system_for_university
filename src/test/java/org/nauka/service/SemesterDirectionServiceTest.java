package org.nauka.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.mapper.SemesterDirectionMapper;
import org.nauka.model.dao.SemesterDirection;
import org.nauka.model.dto.SemesterDirectionDto;
import org.nauka.repository.SemesterDirectionRepository;
import org.nauka.repository.SemesterRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.nauka.model.semesterDirection.SemesterDirectionDaoTest.administrationWithoutId;

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


}
