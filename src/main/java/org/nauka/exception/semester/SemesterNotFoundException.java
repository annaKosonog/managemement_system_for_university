package org.nauka.exception.semester;

public class SemesterNotFoundException extends RuntimeException {
    public SemesterNotFoundException(Long id) {
        super("Not found semester by id: " + id);
    }
}
