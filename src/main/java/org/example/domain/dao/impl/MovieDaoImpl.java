package org.example.domain.dao.impl;

import org.example.domain.dao.MovieDao;
import org.example.domain.entity.Genre;
import org.example.domain.entity.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;


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
        Transaction tx = session.beginTransaction();
        List<Movie> movies = null;
        try {
            movies = session.createQuery("from Movie ", Movie.class).list();
            tx.commit();
        } catch (Throwable ex) {
            tx.rollback();
        } finally {
            session.close();
        }
        return movies;
    }

    @Override
    public void update(Movie movie) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(movie);
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
