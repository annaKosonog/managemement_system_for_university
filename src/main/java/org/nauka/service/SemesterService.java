package org.nauka.service;

import org.nauka.repository.SemesterRepository;
import org.springframework.stereotype.Service;

@Service
public class SemesterService {
    private SemesterRepository semesterRepository;

    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }


}
