package org.example.domain.dao.impl;

import org.example.domain.dao.MovieDao;
import org.example.domain.entity.Actor;
import org.example.domain.entity.Director;
import org.example.domain.entity.Genre;
import org.example.domain.entity.Movie;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class MovieDaoImpl implements MovieDao {

    private final SessionFactory sessionFactory;

    public MovieDaoImpl() {
        this.sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }


    @Override
    public void save(Movie... movies) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            for (Movie mov : movies) {
                Integer id = (Integer) session.save(mov);
                System.out.println("Id: " + id);
            }
            tx.commit();
        } catch (Throwable ex) {
            tx.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public Movie find(int id) {
        return null;
    }

    @Override
    public List<Movie> findAll() {
        Session session = sessionFactory.openSession();
        List<Movie> movies = session.createQuery("from Movie ", Movie.class).list();
        for (int i = 0; i < movies.size(); i++) {
            Set<Genre> genres = movies.get(i).getGenreSet();
            for (Genre genre: genres) {
                genre.getGenreName();
            }
            Set<Actor> actors = movies.get(i).getActorSet();
            for (Actor actor: actors) {
                actor.getLastName();
                actor.getFirstName();
            }
            Director director = movies.get(i).getDirector();
            if (director != null) {
                director.getFirstName();
            }
        }
        session.close();
        return movies;
    }

    @Override
    public void update(Movie movie) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(movie);
            tx.commit();
        } catch (Throwable ex) {
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {

            Movie movie = session.get(Movie.class, id);
            session.delete(movie);
            System.out.println("Фильм успешно удален !!!");
            tx.commit();
        } catch (Throwable ex) {
            System.out.println("Возникла ошибка при удалении фильма !!!");
            tx.rollback();
        } finally {
            session.close();
        }
    }
}
