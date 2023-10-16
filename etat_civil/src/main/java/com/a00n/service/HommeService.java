package com.a00n.service;

import com.a00n.dao.IDao;
import com.a00n.entities.Femme;
import com.a00n.entities.Homme;
import com.a00n.entities.Marriage;
import com.a00n.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ay0ub
 */
public class HommeService implements IDao<Homme> {

    @Override
    public boolean create(Homme o) {
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
    public boolean delete(Homme o) {
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
    public boolean update(Homme o) {
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
    public List<Homme> findAll() {
        List<Homme> hommes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            hommes = session.createSelectionQuery("from Homme", Homme.class).getResultList();
            tx.commit();
            return hommes;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return hommes;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Homme findById(int id) {
        Homme homme = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            homme = session.get(Homme.class, id);
            tx.commit();
            return homme;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return homme;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Femme> getEpousesOfHommeBetweenDates(Homme homme, Date startDate, Date endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT m.femme FROM Marriage m WHERE m.homme = :homme AND m.dateDebut >= :startDate AND (m.dateFin IS NULL OR m.dateFin <= :endDate)";

            Query<Femme> query = session.createQuery(hql, Femme.class);
            query.setParameter("homme", homme);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);

            return query.list();
        }
    }

    public Long getHommesMarriedToFourFemmesBetweenDates(Date startDate, Date endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Homme> hommeRoot = criteriaQuery.from(Homme.class);
            Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
            Root<Marriage> subqueryRoot = subquery.from(Marriage.class);

            subquery.select(criteriaBuilder.countDistinct(subqueryRoot.get("homme")))
                    .where(
                            criteriaBuilder.and(
                                    criteriaBuilder.greaterThanOrEqualTo(subqueryRoot.get("dateDebut"), startDate),
                                    criteriaBuilder.or(
                                            criteriaBuilder.isNull(subqueryRoot.get("dateFin")),
                                            criteriaBuilder.lessThanOrEqualTo(subqueryRoot.get("dateFin"), endDate)
                                    )
                            )
                    );

            subquery.groupBy(subqueryRoot.get("homme"));
            subquery.having(criteriaBuilder.ge(criteriaBuilder.countDistinct(subqueryRoot.get("femme")), 4));

            criteriaQuery.select(criteriaBuilder.countDistinct(hommeRoot.get("id")))
                    .where(criteriaBuilder.in(hommeRoot.get("id")).value(subquery));

            TypedQuery<Long> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();

//            Root<Homme> hommeRoot = query.from(Homme.class);
//            Join<Homme, Marriage> marriageJoin = hommeRoot.join("marriageList");
//
//            query.select(builder.countDistinct(hommeRoot.get("id")));
//            query.where(
//                    builder.and(
//                            builder.greaterThanOrEqualTo(marriageJoin.get("dateDebut"), startDate),
//                            builder.or(
//                                    builder.lessThanOrEqualTo(marriageJoin.get("dateFin"), endDate),
//                                    builder.isNull(marriageJoin.get("dateFin"))
//                            )
//                    )
//            );
//            return session.createQuery(query).getSingleResult();
        }
    }
}
