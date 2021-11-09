package repository;

import models.AccountingRecords;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.BooksService;
import service.ClientService;
import utils.HibernateSessionFactoryUtil;

import java.time.LocalDate;
import java.util.List;

public class AccountingRecordsDao {
    private static final Logger log = Logger.getLogger(AccountingRecordsDao.class);

    public AccountingRecords findAccountingRecordsById(int id) {

        AccountingRecords accountingRecords = null;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            accountingRecords = session.get(AccountingRecords.class, id);
        } catch (Exception e) {
            log.error("Error session");
        }
        return accountingRecords;
    }


    public void save(AccountingRecords accountingRecords) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(accountingRecords);
        tx1.commit();
        session.close();
    }


    public void update(AccountingRecords accountingRecords) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(accountingRecords);
        tx1.commit();
        session.close();
    }

    public void deleteById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(session.get(AccountingRecords.class, id));
        tx1.commit();
        session.close();
    }


    public List<AccountingRecords> findAll() {
        List<AccountingRecords> accountingRecords = (List<AccountingRecords>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From AccountingRecords").list();
        return accountingRecords;
    }
}
