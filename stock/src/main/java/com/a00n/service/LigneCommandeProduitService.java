package com.a00n.service;

import com.a00n.dao.IDao;
import com.a00n.entities.LigneCommandeProduit;
import com.a00n.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ay0ub
 */
public class LigneCommandeProduitService implements IDao<LigneCommandeProduit> {

    @Override
    public boolean create(LigneCommandeProduit o) {
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
    public boolean delete(LigneCommandeProduit o) {
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
    public boolean update(LigneCommandeProduit o) {
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
    public List<LigneCommandeProduit> findAll() {
        List<LigneCommandeProduit> ligneCommandeProtuits = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            ligneCommandeProtuits = session.createSelectionQuery("from LigneCommandeProduit", LigneCommandeProduit.class).getResultList();
            tx.commit();
            return ligneCommandeProtuits;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return ligneCommandeProtuits;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public LigneCommandeProduit findById(int id) {
        LigneCommandeProduit ligneCommandeProtuit = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            ligneCommandeProtuit = session.get(LigneCommandeProduit.class, id);
            tx.commit();
            return ligneCommandeProtuit;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return ligneCommandeProtuit;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
