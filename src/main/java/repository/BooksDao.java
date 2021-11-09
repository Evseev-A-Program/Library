package repository;

import lombok.extern.log4j.Log4j;
import models.Books;
import models.Status;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

@Log4j
public class BooksDao {


    public Books findBooksById(int id) {
        Books book = null;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            book = session.get(Books.class, id);
        } catch (HibernateException e) {
            log.error("Error session");
        }
        return book;
    }

    public List<Books> searchBookForName(String name) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Long count = ((Long) session.createQuery("select count(*) from Books where status = 1").uniqueResult());
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
        List<Books> books = (List<Books>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Books where status = 1").list();
        return books;
    }

    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(session.get(Books.class, id));
        tx1.commit();
        session.close();
    }

    public void update(Books books) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(books);
        tx1.commit();
        session.close();
    }


}
