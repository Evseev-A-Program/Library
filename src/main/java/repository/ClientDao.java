package repository;

import models.Clients;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ClientDao {
    private static final Logger log = Logger.getLogger(ClientDao.class);

    public Clients findBooksById(int id) {
        Session session = null;
        Clients client = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            client = (Clients)session.get(Clients.class, id);
        } catch (HibernateException e) {
            log.error("Ошибка в создании сессии");
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return client;
    }

    public List<Clients> findAll() {
        List<Clients> books = (List<Clients>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Clients ").list();
        return books;
    }

    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(session.get(Clients.class, id));
        tx1.commit();
        session.close();
    }

    public void update(int id, String firstname, String lastname) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.get(Clients.class, id).setFirstname(firstname);
        session.get(Clients.class, id).setLastname(lastname);
        session.update(session.get(Clients.class, id));
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
