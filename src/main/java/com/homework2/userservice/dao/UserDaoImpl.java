package com.homework2.userservice.dao;

import com.homework2.userservice.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.persist(user);

        tx.commit();
        session.close();
    }

    @Override
    public List<User> findAll() {

        Session session = sessionFactory.openSession();

        List<User> users = session.createQuery("from User", User.class).list();

        session.close();

        return users;
    }

    @Override
    public User findById(Long id) {

        Session session = sessionFactory.openSession();

        User user = session.get(User.class, id);

        session.close();

        return user;
    }

    @Override
    public void update(User user) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.merge(user);

        tx.commit();
        session.close();
    }

    @Override
    public void delete(User user) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.remove(user);

        tx.commit();
        session.close();
    }
}