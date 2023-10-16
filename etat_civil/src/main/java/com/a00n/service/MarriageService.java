/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.a00n.service;

import com.a00n.dao.IDao;
import com.a00n.entities.Homme;
import com.a00n.entities.Marriage;
import com.a00n.utils.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ay0ub
 */
public class MarriageService implements IDao<Marriage> {
    
    @Override
    public boolean create(Marriage o) {
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
    public boolean delete(Marriage o) {
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
    public boolean update(Marriage o) {
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
    public List<Marriage> findAll() {
        List<Marriage> marriages = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            marriages = session.createSelectionQuery("from Marriage", Marriage.class).getResultList();
            tx.commit();
            return marriages;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return marriages;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    @Override
    public Marriage findById(int id) {
        Marriage marriage = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            marriage = session.get(Marriage.class, id);
            tx.commit();
            return marriage;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return marriage;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public void displayMarriagesDetails(Homme homme) {
        System.out.println("Nom : " + homme.getNom());
        displayOngoingMarriages(homme);
        displayFailedMarriages(homme);
    }
    
    private void displayOngoingMarriages(Homme homme) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Mariages En Cours :");
            
            String sql = "SELECT m FROM Marriage m "
                    + "WHERE m.homme = :homme "
                    + "AND (m.dateFin IS NULL OR m.dateFin >= CURRENT_DATE)";
            Query query = session.createQuery(sql, Marriage.class);
            query.setParameter("homme", homme);
            List<Marriage> ongoingMarriages = query.getResultList();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            for (int i = 0; i < ongoingMarriages.size(); i++) {
                Marriage marriage = ongoingMarriages.get(i);
                System.out.println((i + 1) + ". Femme : " + marriage.getFemme().getNom()
                        + " Date Début : " + dateFormat.format(marriage.getDateDebut())
                        + " Nbr Enfants : " + marriage.getNbEnfants());
            }
        }
        
    }
    
    private void displayFailedMarriages(Homme homme) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Mariages échoués :");
            
            String sql = "SELECT m FROM Marriage m "
                    + "WHERE m.homme = :homme "
                    + "AND m.dateFin IS NOT NULL "
                    + "AND m.dateFin < m.dateDebut";
            Query query = session.createQuery(sql, Marriage.class);
            query.setParameter("homme", homme);
            List<Marriage> failedMarriages = query.getResultList();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            for (int i = 0; i < failedMarriages.size(); i++) {
                Marriage marriage = failedMarriages.get(i);
                System.out.println((i + 1) + ". Femme : " + marriage.getFemme().getNom()
                        + " Date Début : " + dateFormat.format(marriage.getDateDebut())
                        + " Date Fin : " + dateFormat.format(marriage.getDateFin())
                        + " Nbr Enfants : " + marriage.getNbEnfants());
            }
        }
        
    }
}
