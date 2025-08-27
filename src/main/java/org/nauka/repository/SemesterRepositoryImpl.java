package org.nauka.repository;

import org.nauka.model.dao.Semester;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SemesterRepositoryImpl implements SemesterRepository {

    private final List<Semester> semesters = new ArrayList<>();

    @Override
    public Semester findIdBySemester(Long idSemester) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Semester> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Semester> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Semester> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Semester getOne(Long aLong) {
        return null;
    }

    @Override
    public Semester getById(Long aLong) {
        return null;
    }

    @Override
    public Semester getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Semester> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Semester> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Semester> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Semester> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Semester> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Semester> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Semester, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Semester> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Semester> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Semester> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Semester> findAll() {
        return null;
    }

    @Override
    public List<Semester> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Semester entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Semester> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Semester> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Semester> findAll(Pageable pageable) {
        return null;
    }

   /* @Override
    public Semester findIdBySemester(Long idSemester) {
        return semesters.stream()
                .filter(semester -> semester.getSemesterId().equals(idSemester))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found id semester" + idSemester));
    }

    @Override
    public void clear() {
        semesters.clear();
    }*/
}
