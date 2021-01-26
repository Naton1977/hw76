package org.example.domain.dao;

import org.example.domain.entity.Genre;

import java.util.List;

public interface GenreDao {
    void save(Genre... genres);

    Genre find(int id);

    List<Genre> findAll();

    void update(Genre genre);

    void delete(int id);
}
