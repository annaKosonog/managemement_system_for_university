package org.nauka.controller;

import org.nauka.model.dao.Semester;
import org.nauka.service.SemesterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/semesters")
public class SemesterController {


    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    private final SemesterService semesterService;

    @PostMapping
    public ResponseEntity<Semester> addNewSemester(@RequestBody Semester semester) {
        Semester newSemester = semesterService.createNewSemester(semester);
        return ResponseEntity.ok(newSemester);
    }
}
