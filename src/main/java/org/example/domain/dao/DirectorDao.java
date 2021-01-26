package org.example.domain.dao;

import org.example.domain.entity.Director;

import java.util.List;

public interface DirectorDao {
    void save(Director... directors);

    Director find(int id);

    List<Director> findAll();

    void update(Director directors);

    void delete(int id);
}
