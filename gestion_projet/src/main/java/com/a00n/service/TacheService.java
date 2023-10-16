package com.a00n.service;

import com.a00n.dao.IDao;
import com.a00n.entities.Tache;
import com.a00n.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ay0ub
 */
public class TacheService implements IDao<Tache> {

    @Override
    public boolean create(Tache o) {
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
    public boolean delete(Tache o) {
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
    public boolean update(Tache o) {
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
    public List<Tache> findAll() {
        List<Tache> taches = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            taches = session.createSelectionQuery("from Tache", Tache.class).getResultList();
            tx.commit();
            return taches;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return taches;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Tache findById(int id) {
        Tache tache = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            tache = session.get(Tache.class, id);
            tx.commit();
            return tache;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return tache;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void findPriceGreaterThen1000() {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            session.createNamedQuery("Tache.findPriceGreaterThen1000", Tache.class)
                    .getResultList().forEach(tache -> System.out.println(tache.getNom()));
        });
    }

}
