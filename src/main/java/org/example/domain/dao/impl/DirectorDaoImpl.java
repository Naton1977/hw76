package org.example.domain.dao.impl;

import org.example.domain.dao.DirectorDao;
import org.example.domain.entity.Director;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DirectorDaoImpl implements DirectorDao {

    private final SessionFactory sessionFactory;

    public DirectorDaoImpl() {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }


    @Override
    public void save(Director... directors) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            for (Director dir : directors) {
                Integer id = (Integer) session.save(dir);
                System.out.println("Id: " + id);
            }
            System.out.println("Режисер успешно сохранен !!!");
            tx.commit();
        } catch (Throwable ex) {
            System.out.println("Возникла ошибка при сохранении данных режисера !!!");
            tx.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public Director find(int id) {
        Director director;
        Session session = sessionFactory.openSession();
        director = session.get(Director.class, id);
        session.close();
        return director;
    }

    @Override
    public List<Director> findAll() {
        Session session = sessionFactory.openSession();
        List<Director> directors = session.createQuery("from Director ", Director.class).list();
        session.close();
        return directors;
    }

    @Override
    public void update(Director directors) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(directors);
            tx.commit();
            System.out.println("Данные режисера успешно обновленны !!!");
        } catch (Throwable ex) {
            tx.rollback();
            System.out.println("Возникла ошибка при обновлении данных режисера !!!");
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {

            Director director = session.get(Director.class, id);
            session.delete(director);
            tx.commit();
            System.out.println("Данные режисера успешно удалены !!!");
        } catch (Throwable ex) {
            tx.rollback();
            System.out.println("Возникла ошибка при удалении данных режисера !!!");
        } finally {
            session.close();
        }
    }
}
