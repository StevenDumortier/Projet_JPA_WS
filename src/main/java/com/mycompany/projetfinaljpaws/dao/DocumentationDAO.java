/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetfinaljpaws.dao;

import com.mycompany.projetfinaljpaws.entity.Documentation;
import com.mycompany.projetfinaljpaws.jpa.EntityManagerSingleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author steven
 */
public class DocumentationDAO {

    public static void create(Documentation d) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(d);
        tx.commit();
    }

    public static Documentation findById(Long id) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        Documentation documentation = entityManager.find(Documentation.class, id);
        return documentation;

    }

    public static List<Documentation> findAll() {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        Query findAllQuery = entityManager.createQuery("select c from Documentation c");
        return findAllQuery.getResultList();
    }

    public static void delete(Documentation documentation) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove(documentation);
        tx.commit();
    }

    public static void deleteCustomerById(Long id) {

        Documentation documentationToDelete = findById(id); //on utilise les fonctions au dessus donc pas besoin de faire transaction et entitymanager
        delete(documentationToDelete);
    }

    public static void update(Long id, Documentation newDocumentationDate) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();

        Documentation documentationToUpdate = entityManager.find(Documentation.class, id);
        documentationToUpdate.setNotNullData(newDocumentationDate);
        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(documentationToUpdate);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public static List<Documentation> findByName(String name) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();

        Query queryToFindCategoryByName = entityManager.createQuery("select c from Documentation c where c.name = :name");
        queryToFindCategoryByName.setParameter("name", name);
        return queryToFindCategoryByName.getResultList();
    }

}
