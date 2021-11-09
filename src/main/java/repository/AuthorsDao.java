package repository;

import models.Authors;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class AuthorsDao {
    private static final Logger log = Logger.getLogger(AuthorsDao.class);

    public Authors findAuthorsById(int id) {
        Session session = null;
        Authors author = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            author = (Authors)session.get(Authors.class, id);
        } catch (HibernateException e) {
            log.error("Ошибка в создании сессии");
            System.out.println("Исключение: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return author;
    }

    public List<Authors> searchBookForNameAuthors(String name) {


        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Long count = ((Long) session.createQuery("select count(*) from Authors").uniqueResult());
        Integer totalAuthors = count.intValue();
        session.close();
        AuthorsDao authorsDao = new AuthorsDao();
        List<Authors> authors = new ArrayList<>();
        for (int i = 1; i<=totalAuthors; i++){
            Authors author = authorsDao.findAuthorsById(i);
            if (author.getFirstname().startsWith(name) || author.getLastname().startsWith(name)) authors.add(author);
        }

        return authors;
    }

    public void save(Authors authors) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(authors);
        tx1.commit();
        session.close();
    }

    public void update(Authors authors) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(authors);
        tx1.commit();
        session.close();
    }

    public void updateID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(session.get(Authors.class, id));
        tx1.commit();
        session.close();
    }

    public void delete(Authors authors) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(authors);
        tx1.commit();
        session.close();
    }


    public List<Authors> findAll() {
        List<Authors> authors = (List<Authors>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Books where availability = false").list();
        return authors;
    }
}