package org.example.domain.dao.impl;

import org.example.domain.dao.GenreDao;
import org.example.domain.entity.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GenreDaoImpl implements GenreDao {
    private final SessionFactory sessionFactory;

    public GenreDaoImpl() {
        this.sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }


    @Override
    public void save(Genre... genres) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            for (Genre gen : genres) {
                Integer id = (Integer) session.save(gen);
                System.out.println("Жанр успешно добавлен !!!");
                System.out.println("Id: " + id);
            }
            tx.commit();
        } catch (Throwable ex) {
            System.out.println("Возникла ошибка при добавлении жанра !!!");
            tx.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public Genre find(int id) {
        return null;
    }

    @Override
    public List<Genre> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Genre> genres = null;
        try {
            genres = session.createQuery("from Genre ", Genre.class).list();
            tx.commit();
        } catch (Throwable ex) {
            tx.rollback();
        } finally {
            session.close();
        }
        return genres;
    }

    @Override
    public void update(Genre genre) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(genre);
            tx.commit();
            System.out.println("Название жанра успешно обновленно !!!");
        } catch (Throwable ex) {
            tx.rollback();
            System.out.println("Возникла ошибка при обновлении названия жанра !!!");
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {

            Genre genre = session.get(Genre.class, id);
            session.delete(genre);
            System.out.println("Жанр успешно удален !!!");
            tx.commit();
        } catch (Throwable ex) {
            System.out.println("Возникла ошибка при удалении жанра !!!");
            tx.rollback();
        } finally {
            session.close();
        }

    }
}
