package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateDemoMain {

    public static void main(String[] args) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();

            // Create a NEW user (transient) — do NOT supply id
            UserClassHibernate user = new UserClassHibernate("Navinder", "nnavigill752@gmail.com");

            session.persist(user); // OK: user.id is null -> insert

            tx.commit();

            System.out.println("User saved in database, id = " + user.getId());
        } catch (Exception e) {
            if (tx != null && tx.getStatus().canRollback()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();    // important — returns connection to pool
            }
            HibernateUtil.shutdown(); // close SessionFactory on app shutdown
        }
    }
}
