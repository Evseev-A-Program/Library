package repository;

import lombok.extern.log4j.Log4j;
import models.Clients;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

@Log4j
public class ClientDao {

    public Clients findClientById(int id) {
        Clients client = null;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            client = session.get(Clients.class, id);
        } catch (HibernateException e) {
            log.error("Error session");
        }
        return client;
    }

    public List<Clients> findAll() {
        List<Clients> books = (List<Clients>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Clients ").list();
        return books;
    }

    public void delete(Clients clients) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(clients);
        tx1.commit();
        session.close();
    }

    public void update(Clients clients) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(clients);
        tx1.commit();
        session.close();
    }

    public void save(Clients clients) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(clients);
        tx1.commit();
        session.close();
    }
}
