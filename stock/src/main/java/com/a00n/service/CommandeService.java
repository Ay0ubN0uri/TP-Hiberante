package com.a00n.service;

import com.a00n.dao.IDao;
import com.a00n.entities.Commande;
import com.a00n.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ay0ub
 */
public class CommandeService implements IDao<Commande> {

    @Override
    public boolean create(Commande o) {
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
    public boolean delete(Commande o) {
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
    public boolean update(Commande o) {
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
    public List<Commande> findAll() {
        List<Commande> commandes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            commandes = session.createSelectionQuery("from Commande", Commande.class).getResultList();
            tx.commit();
            return commandes;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return commandes;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Commande findById(int id) {
        Commande commande = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            commande = session.get(Commande.class, id);
            tx.commit();
            return commande;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return commande;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
