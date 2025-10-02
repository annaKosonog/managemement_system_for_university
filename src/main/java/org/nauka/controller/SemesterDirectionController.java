package org.nauka.controller;

import lombok.RequiredArgsConstructor;
import org.nauka.model.dao.SemesterDirection;
import org.nauka.service.SemesterDirectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/direction")
public class SemesterDirectionController {

    private final SemesterDirectionService semesterDirectionService;


    @PostMapping("/name/{name}")
    public ResponseEntity<SemesterDirection> addNewDirection(@PathVariable String name) {
        SemesterDirection direction = semesterDirectionService.createNewSemesterDirection(name);
        return ResponseEntity.ok(direction);
    }


}
