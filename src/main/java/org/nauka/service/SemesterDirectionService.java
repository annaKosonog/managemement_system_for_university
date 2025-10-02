package org.nauka.service;

import lombok.RequiredArgsConstructor;
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


}
