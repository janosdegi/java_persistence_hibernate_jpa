package H_jpa__hibernate_as_jpa_provider.client;//package com.springsecuritycourse.config;
//
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
///**
// * Created by Dégi János on 2017.09.23..
// */
//@Component
//public enum PersistenceManager {
//
//    INSTANCE;
//
//    private EntityManagerFactory emFactory;
//
//    private PersistenceManager() {
//        // "jpa-example" was the value of the name attribute of the
//        // persistence-unit element.
//        emFactory = Persistence.createEntityManagerFactory("jpa_envisy");
//    }
//
//    public EntityManager getEntityManager() {
//        return emFactory.createEntityManager();
//    }
//
//    public void close() {
//        emFactory.close();
//    }
//
//}
