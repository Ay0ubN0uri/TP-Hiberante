package com.a00n.service;

import com.a00n.dao.IDao;
import com.a00n.entities.EmployeTache;
import com.a00n.entities.Projet;
import com.a00n.entities.Tache;
import com.a00n.utils.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ay0ub
 */
public class ProjetService implements IDao<Projet> {

    @Override
    public boolean create(Projet o) {
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
    public boolean delete(Projet o) {
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
    public boolean update(Projet o) {
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
    public List<Projet> findAll() {
        List<Projet> projets = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            projets = session.createSelectionQuery("from Projet", Projet.class).getResultList();
            tx.commit();
            return projets;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return projets;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Projet findById(int id) {
        Projet projet = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            projet = session.get(Projet.class, id);
            tx.commit();
            return projet;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return projet;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Tache> getPlannedTasksForProject(Projet project) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Date today = new Date();
            String hql = "SELECT t FROM Tache t "
                    + "WHERE t.projetId = :project AND t.dateFin > :today";

            Query<Tache> query = session.createQuery(hql, Tache.class);
            query.setParameter("project", project);
            query.setParameter("today", today);

            return query.list();
        }
    }

    public void getCompletedTasksInProject(Projet project) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Date today = new Date();
//            String hql = "SELECT t FROM Tache t "
//                    + "WHERE t.projetId = :project AND t.dateFin <= :today";

            String hql = "FROM Tache t WHERE t.projetId = :project AND t.id IN "
                    + "(SELECT et.tache.id FROM EmployeTache et WHERE et.dateFinReelle <= :today)";

            Query<Tache> query = session.createQuery(hql, Tache.class);
            query.setParameter("project", project);
            query.setParameter("today", today);

            System.out.println("Projet : " + project.getNom() + "\t\tDate : " + project.getDateDebut());
            System.out.println("listes des taches : ");
            System.out.println("Num\t\t\tNom\t\t\tDate Début Réelle\t\t\tDate Fin Réelle");
            query.getResultList().forEach(tache -> {
                for (EmployeTache et : tache.getEmployeTacheList()) {
                    System.out.println(tache.getId() + "\t\t\t" + tache.getNom() + "\t\t\t" + et.getDateDebutReelle() + "\t\t\t" + et.getDateFinReelle());
                }
            });

        }
    }

    public void displayRealizedTasksBetweenDates(Date startDate, Date endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Tache t WHERE t.id IN "
                    + "(SELECT et.tache.id FROM EmployeTache et WHERE et.dateFinReelle >= :startDate AND et.dateFinReelle <= :endDate)";

            Query<Tache> query = session.createQuery(hql, Tache.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);

            query.getResultList().forEach(tache -> System.out.println(tache));

        }
    }

}
