package com.dss.movie.repository;

import com.dss.movie.model.Movie;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MovieDaoImpl implements MovieDao {
    @Override
    public List<Movie> findAll() {
        return null;
    }

    @Override
    public List<Movie> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Movie> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Movie> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Movie entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Movie> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Movie> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Movie> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Movie> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Movie> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Movie> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Movie> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Movie getOne(String s) {
        return null;
    }

    @Override
    public Movie getById(String s) {
        return null;
    }

    @Override
    public Movie getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends Movie> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Movie> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Movie> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Movie> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Movie> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Movie> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Movie, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
