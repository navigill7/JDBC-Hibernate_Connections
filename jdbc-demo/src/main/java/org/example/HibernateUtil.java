package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(UserClassHibernate.class)
                    .buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Initial SessionFactory creation failed.");
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    // Return a new session. Caller must close it.
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
