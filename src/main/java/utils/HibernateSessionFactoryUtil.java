package utils;

import models.AccountingRecords;
import models.Authors;
import models.Books;
import models.Clients;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Authors.class);
                configuration.addAnnotatedClass(Books.class);
                configuration.addAnnotatedClass(AccountingRecords.class);
                configuration.addAnnotatedClass(Clients.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (HibernateException e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}