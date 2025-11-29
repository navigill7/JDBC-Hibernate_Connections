package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JAPUtil {

    private static final EntityManagerFactory emf ;

    static {
        emf = Persistence.createEntityManagerFactory("myJpaUnit");

    }

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public static void close(){
        emf.close();
    }




}
