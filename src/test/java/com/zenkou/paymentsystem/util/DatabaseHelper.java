package com.zenkou.paymentsystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Component
public class DatabaseHelper {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public DatabaseHelper(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void cleanDatabase() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from User").executeUpdate();
        entityManager.createQuery("delete from Address").executeUpdate();
        entityManager.createQuery("delete from Payment").executeUpdate();
        entityManager.createQuery("delete from BankAccount").executeUpdate();
        entityManager.createQuery("delete from Role").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
