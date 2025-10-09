package org.nauka.model.semesterDirection;

import org.nauka.model.dao.SemesterDirection;

import java.util.List;

import static org.nauka.model.SemesterTest.semesterWinter;
import static org.nauka.model.student.StudentDaoTestData.alaKotWithId;

public class SemesterDirectionDaoTest {

    public static SemesterDirection administration() {
        return new SemesterDirection(1L, "ADMINISTRATION", List.of(semesterWinter), alaKotWithId(), List.of());
    }

    public static SemesterDirection computerScience() {
        return new SemesterDirection(2L, "COMPUTER SCIENCE", List.of(semesterWinter), null, List.of());
    }
}
