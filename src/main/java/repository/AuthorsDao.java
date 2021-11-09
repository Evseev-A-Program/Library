package repository;

import models.Authors;
import models.Books;
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
        Authors author = null;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            author = session.get(Authors.class, id);
        } catch (HibernateException e) {
            log.error("Error session");
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

}