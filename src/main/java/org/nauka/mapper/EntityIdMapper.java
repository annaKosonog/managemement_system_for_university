package org.nauka.mapper;

import org.nauka.model.dao.*;
import org.nauka.model.dto.SemesterDirectionDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityIdMapper {

    public Student mapStudent(Long id) {
        if (id == null) return null;
        Student s = new Student();
        s.setIdStudent(id);
        return s;
    }

    public Long mapStudent(Student student) {
        return student != null ? student.getIdStudent() : null;
    }

    public List<Student> mapStudentList(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(this::mapStudent).collect(Collectors.toList());
    }

    public List<Long> mapStudentIdList(List<Student> students) {
        if (students == null) return null;
        return students.stream().map(this::mapStudent).collect(Collectors.toList());
    }

    public TuitionFee mapTuitionFee(Long id) {
        if (id == null) return null;
        TuitionFee t = new TuitionFee();
        t.setTuitionFeeId(id);
        return t;
    }

    public Long mapTuitionFee(TuitionFee tuitionFee) {
        return tuitionFee != null ? tuitionFee.getTuitionFeeId() : null;
    }

    public List<TuitionFee> mapTuitionFeeList(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(this::mapTuitionFee).collect(Collectors.toList());
    }

    public List<Long> mapTuitionFeeIdList(List<TuitionFee> list) {
        if (list == null) return null;
        return list.stream().map(this::mapTuitionFee).collect(Collectors.toList());
    }


    public List<Payment> mapPaymentList(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            Payment p = new Payment();
            p.setPaymentId(id);
            return p;
        }).collect(Collectors.toList());
    }

    public List<Long> mapPaymentIdList(List<Payment> payments) {
        if (payments == null) return null;
        return payments.stream().map(Payment::getPaymentId).collect(Collectors.toList());
    }

    public List<SemesterDirection> mapSemesterDirectionList(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            SemesterDirection sd = new SemesterDirection();
            sd.setIdSemesterDirection(id);
            return sd;
        }).collect(Collectors.toList());
    }

    public Semester mapSemester(Long id) {
        if (id == null) return null;
        Semester s = new Semester();
        s.setSemesterId(id);
        return s;
    }

    public Long mapSemester(Semester s) {
        return s != null ? s.getSemesterId() : null;
    }

    public List<Long> mapSemesterDirectionIdList(List<SemesterDirection> list) {
        if (list == null) return null;
        return list.stream().map(SemesterDirection::getIdSemesterDirection).collect(Collectors.toList());
    }

    public List<Semester> mapSemesterList(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(this::mapSemester).collect(Collectors.toList());
    }

    public List<Long> mapSemesterIdList(List<Semester> list) {
        if (list == null) return null;
        return list.stream().map(this::mapSemester).collect(Collectors.toList());
    }

    public SemesterDirection map(SemesterDirectionDto dto) {
        if (dto == null) return null;
        SemesterDirection sd = new SemesterDirection();
        sd.setIdSemesterDirection(dto.getIdSemesterDirection());
        return sd;
    }

    public SemesterDirection map(Long id) {
        if (id == null) return null;
        SemesterDirection sd = new SemesterDirection();
        sd.setIdSemesterDirection(id);
        return sd;
    }

    public Long map(SemesterDirection sd) {
        return sd != null ? sd.getIdSemesterDirection() : null;
    }
}
