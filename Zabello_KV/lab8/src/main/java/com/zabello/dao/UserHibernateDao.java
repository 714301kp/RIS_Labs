package com.zabello.dao;

import com.zabello.generated.User;
import org.hibernate.Session;

import org.springframework.stereotype.Repository;

import java.util.List;

import static com.zabello.utils.HibernateUtil.getSessionFactory;


@Repository
public class UserHibernateDao {

    public void createUsers(List<User> users){

        Session session = getSessionFactory().getCurrentSession();

        session.beginTransaction();

        for(User user: users){
            session.save(user);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void createUser(User user){
        Session session = getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.save(user);
        session.close();
    }

    public List<User> readUsers() {

        Session session = getSessionFactory().getCurrentSession();

        session.beginTransaction();

        List<User>users = session.createCriteria(User.class).list();

        session.close();
        return  users;
    }

    public User readUserById(long id){
        Session session = getSessionFactory().getCurrentSession();

        session.beginTransaction();

        User user = session.get(User.class, id);

        session.close();

        return user;
    }

    public void updateUser(User user){

        Session session = getSessionFactory().getCurrentSession();

        session.beginTransaction();

        session.merge(user);

        session.getTransaction().commit();

        session.close();
    }

    public void deleteUser(long id){

        Session session = getSessionFactory().getCurrentSession();

        session.beginTransaction();

        User user = session.load(User.class, id);

        session.delete(user);

        session.getTransaction().commit();

        session.close();
    }
}
