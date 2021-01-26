package org.example.domain.dao;

import org.example.domain.entity.Movie;

import java.util.List;

public interface MovieDao {
    void save(Movie... movies);

    Movie find(int id);

    List<Movie> findAll();

    void update(Movie movie);

    void delete(int id);
}
