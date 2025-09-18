package org.nauka.exception.student;

public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(Long indexNumber) {
        super("Student with this index number already exists" + indexNumber);
    }
}
