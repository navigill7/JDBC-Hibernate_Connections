package org.example;

import jakarta.persistence.EntityManager;

public class JPADemoMain {

    static void main() {

        EntityManager em = JAPUtil.getEntityManager();

        try {
            UserClassHibernate user = new UserClassHibernate("Navi" ,"gillnavi752@gmail.com" );

            em.getTransaction().begin();

            em.persist(user);

            em.getTransaction().commit();

            System.out.println("User Saved with id : " + user.getId() );
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            em.close();

            JAPUtil.close();
        }
    }
}
