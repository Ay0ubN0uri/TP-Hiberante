package com.a00n.service;

import com.a00n.dao.IDao;
import com.a00n.entities.Employee;
import com.a00n.entities.Projet;
import com.a00n.entities.Tache;
import com.a00n.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ay0ub
 */
public class EmployeeService implements IDao<Employee> {

    @Override
    public boolean create(Employee o) {
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
    public boolean delete(Employee o) {
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
    public boolean update(Employee o) {
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
    public List<Employee> findAll() {
        List<Employee> employees = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employees = session.createSelectionQuery("from Employee", Employee.class).getResultList();
            tx.commit();
            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return employees;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Employee findById(int id) {
        Employee employee = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employee = session.get(Employee.class, id);
            tx.commit();
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return employee;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Tache> getTasksRealizedByEmployee(int employeeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT t FROM Tache t "
                    + "JOIN t.employeTacheList et "
                    + "JOIN et.employee e "
                    + "WHERE e.id = :employeeId";

            Query<Tache> query = session.createQuery(hql, Tache.class);
            query.setParameter("employeeId", employeeId);

            return query.list();
        }
    }

    public List<Projet> getProjectsManagedByEmployee(int employeeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM Projet p "
                    + "JOIN p.tacheList t "
                    + "JOIN t.employeTacheList et "
                    + "JOIN et.employee e "
                    + "WHERE e.id = :employeeId";

            Query<Projet> query = session.createQuery(hql, Projet.class);
            query.setParameter("employeeId", employeeId);

            return query.list();
        }
    }

}
