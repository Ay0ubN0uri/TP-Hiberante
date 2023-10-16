/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.a00n.service;

import com.a00n.dao.IDao;
import com.a00n.entities.Femme;
import com.a00n.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ay0ub
 */
public class FemmeService implements IDao<Femme> {

    @Override
    public boolean create(Femme o) {
        try {
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                session.persist(o);
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Femme o) {
        try {
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                session.remove(o);
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Femme o) {
        try {
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                session.merge(o);
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Femme> findAll() {
        List<Femme> femmes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femmes = session.createSelectionQuery("from Femme", Femme.class).getResultList();
            tx.commit();
            return femmes;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return femmes;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Femme findById(int id) {
        Femme femme = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femme = session.get(Femme.class, id);
            tx.commit();
            return femme;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return femme;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Long getNumberOfChildrenBetweenDates(Femme femme, Date startDate, Date endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createNamedQuery("Femme.findNumberOfChildrenBetweenDates");
            query.setParameter("femme", femme);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            Long numberOfChildren = (Long) query.getSingleResult();
            return numberOfChildren;
        }
    }

    public List<Femme> getFemmesMarriedMultipleTimes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Femme> query = session.createNamedQuery("Femme.findFemmesMarriedMultipleTimes", Femme.class);
            List<Femme> femmes = query.getResultList();
            return femmes;
        }
    }
}
