package org.example.domain.dao;

import org.example.domain.entity.Actor;

import java.util.List;

public interface ActorDao {
    void save(Actor... actors);

    Actor find(int id);

    List<Actor> findAll();

    void update(Actor actors);

    void delete(int id);
}
