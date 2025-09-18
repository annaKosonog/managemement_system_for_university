package org.nauka.exception.tuitionFee;

public class TuitionFeeNotFoundException extends RuntimeException{

    public TuitionFeeNotFoundException(Long id) {
        super("TuitionFee with id: " + id + " not found");
    }
}
