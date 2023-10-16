package com.a00n.service;

import com.a00n.dao.IDao;
import com.a00n.entities.EmployeTache;
import com.a00n.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ay0ub
 */
public class EmployeTacheService implements IDao<EmployeTache> {

    @Override
    public boolean create(EmployeTache o) {
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
    public boolean delete(EmployeTache o) {
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
    public boolean update(EmployeTache o) {
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
    public List<EmployeTache> findAll() {
        List<EmployeTache> employeTaches = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employeTaches = session.createSelectionQuery("from EmployeTache", EmployeTache.class).getResultList();
            tx.commit();
            return employeTaches;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return employeTaches;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public EmployeTache findById(int id) {
        EmployeTache employeTache = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employeTache = session.get(EmployeTache.class, id);
            tx.commit();
            return employeTache;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return employeTache;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
