package org.nauka.repository;

import lombok.RequiredArgsConstructor;
import org.nauka.model.dao.TuitionFee;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public class TuitionFeeRepositoryImpl implements TuitionFeeRepository {
    @Override
    public void flush() {
    }

    @Override
    public <S extends TuitionFee> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends TuitionFee> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<TuitionFee> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public TuitionFee getOne(Long aLong) {
        return null;
    }

    @Override
    public TuitionFee getById(Long aLong) {
        return null;
    }

    @Override
    public TuitionFee getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends TuitionFee> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends TuitionFee> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends TuitionFee> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends TuitionFee> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TuitionFee> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TuitionFee> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends TuitionFee, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends TuitionFee> S save(S entity) {
        return null;
    }

    @Override
    public <S extends TuitionFee> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<TuitionFee> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<TuitionFee> findAll() {
        return null;
    }

    @Override
    public List<TuitionFee> findAllById(Iterable<Long> longs) {
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
    public void delete(TuitionFee entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends TuitionFee> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<TuitionFee> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TuitionFee> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void clear() {

    }

    //    private final List<TuitionFee> tuitionFeeList = new ArrayList<>();
   /* private final Map<Long, TuitionFee> tuitionFeeMap = new HashMap<>();

    public void save(TuitionFee fee) {
        tuitionFeeMap.put(fee.getSemester().getSemesterId(), fee);
    }

    @Override
    public List<TuitionFee> findAll() {
        return new ArrayList<>(tuitionFeeMap
                .values());
    }

    @Override
    public TuitionFee findById(Long idTuitionFee) {
        return tuitionFeeMap
                .values()
                .stream()
                .filter(tuitionFee -> tuitionFee.getTuitionFeeId().equals(idTuitionFee))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found id: " + idTuitionFee));
    }*/
}
