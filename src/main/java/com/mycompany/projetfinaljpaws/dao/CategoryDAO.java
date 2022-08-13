/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetfinaljpaws.dao;

import com.mycompany.projetfinaljpaws.entity.Category;
import com.mycompany.projetfinaljpaws.jpa.EntityManagerSingleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author steven
 */
public class CategoryDAO {

    public static void create(Category c) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(c);
        tx.commit();
    }

    public static Category findById(Long id) throws Exception {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        Category category = entityManager.find(Category.class, id);
        return category;

    }

    public static List<Category> findAll() {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        Query findAllQuery = entityManager.createQuery("select c from Category c");
        return findAllQuery.getResultList();

    }

    public static void delete(Category category) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove(category);
        tx.commit();
    }

    public static void deleteCustomerById(long id) throws Exception {

        Category categoryToDelete = findById(id); //on utilise les fonctions au dessus donc pas besoin de faire transaction et entitymanager
        delete(categoryToDelete);
    }

    public static void update(Long id, Category newCategoryDate) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();

        Category categoryToUpdate = entityManager.find(Category.class, id);
        categoryToUpdate.setNotNullData(newCategoryDate);
        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(categoryToUpdate);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public static Category findByName(String name) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        Category category = entityManager.find(Category.class, name);
        return category;
    }

}
