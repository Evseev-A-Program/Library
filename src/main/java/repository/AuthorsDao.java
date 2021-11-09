package repository;

import lombok.extern.log4j.Log4j;
import models.Authors;
import models.Books;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

@Log4j
public class AuthorsDao {

    public Authors findAuthorsById(int id) {
        Authors author = null;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            author = session.get(Authors.class, id);
        } catch (HibernateException e) {
            log.error("Error session");
        }
        return author;
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