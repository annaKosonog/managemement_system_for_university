package org.nauka.model;

import org.nauka.model.dao.Student;

public class StudentDaoTestData {
    public static Student adamKowalskiWithoutId(){
        return new Student( "Adam", 112233L, "112233@student.wwe.pl");
    }

    public static Student adamKowalskiWithId(){
        return new Student( 1L, "Adam", 112233L, "112233@student.wwe.pl");
    }

    public static Student alaKot(){
        return new Student(2L, "Ala", 114477L, "114477@student.wwe.pl");
    }
}
