package org.example.domain.dao.impl;

import org.example.domain.dao.ActorDao;
import org.example.domain.entity.Actor;
import org.example.domain.entity.Director;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ActorDaoImpl implements ActorDao {

    private final SessionFactory sessionFactory;


    public ActorDaoImpl() {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }


    @Override
    public void save(Actor... actors) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            for (Actor act : actors) {
                Integer id = (Integer) session.save(act);
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
    public Actor find(int id) {
        Actor actor;
        Session session = sessionFactory.openSession();
        actor = session.get(Actor.class, id);
        session.close();
        return actor;
    }

    @Override
    public List<Actor> findAll() {
        Session session = sessionFactory.openSession();
        List<Actor> actors = session.createQuery("from Actor ", Actor.class).list();
        session.close();
        return actors;
    }

    @Override
    public void update(Actor actors) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(actors);
            tx.commit();
            System.out.println("Данные актера успешно обновленны !!!");
        } catch (Throwable ex) {
            tx.rollback();
            System.out.println("Возникла ошибка при обновлении данных актера !!!");
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {

            Actor actor = session.get(Actor.class, id);
            session.delete(actor);
            tx.commit();
            System.out.println("Данные актера успешно удалены !!!");
        } catch (Throwable ex) {
            tx.rollback();
            System.out.println("Возникла ошибка при удалении данных актера !!!");
        } finally {
            session.close();
        }
    }
}
