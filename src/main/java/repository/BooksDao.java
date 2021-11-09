package repository;

import models.Books;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class BooksDao {
    private static final Logger log = Logger.getLogger(BooksDao.class);

    public Books findBooksById(int id) {
        Session session = null;
        Books book = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            book = (Books)session.get(Books.class, id);
        } catch (HibernateException e) {
            log.error("Ошибка в создании сессии");
            System.out.println("Исключение: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return book;
    }

    public List<Books> searchBookForName(String name) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Long count = ((Long) session.createQuery("select count(*) from Books").uniqueResult());
        Integer totalBooks = count.intValue();
        tx1.commit();
        session.close();
        BooksDao booksDao = new BooksDao();
        List<Books> books = new ArrayList<>();
        for (int i = 1; i<=totalBooks; i++){
            Books book = booksDao.findBooksById(i);
           if (book.getName().startsWith(name)) books.add(book);
        }

        return books;
    }

    public List<Books> findAll() {
        List<Books> books = (List<Books>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Books").list();
        return books;
    }

    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(session.get(Books.class, id));
        tx1.commit();
        session.close();
    }

    public void update(int id, String name, String category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.get(Books.class, id).setName(name);
        session.get(Books.class, id).setCategoryId(category);
        session.update(session.get(Books.class, id));
        tx1.commit();
        session.close();
    }

    public void updateAvailability(int id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.get(Books.class, id).setAvailability(false);
        session.update(session.get(Books.class, id));
        tx1.commit();
        session.close();
    }

}
