package edu.sabanciuniv.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtils {

    static EntityManagerFactory entityManagerFactory = null;
    public static EntityManager getEntityManager(String persistenceUnitName) {
        if(entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        }
        return entityManagerFactory.createEntityManager();
    }
    public static void closeEntityManager(EntityManager entityManager){
        entityManager.clear();
        entityManager.close();
    }
}

