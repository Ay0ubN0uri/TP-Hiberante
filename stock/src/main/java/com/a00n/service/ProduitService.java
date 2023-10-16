package com.a00n.service;

import com.a00n.dao.IDao;
import com.a00n.entities.Commande;
import com.a00n.entities.LigneCommandeProduit;
import com.a00n.entities.Produit;
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
public class ProduitService implements IDao<Produit> {

    @Override
    public boolean create(Produit o) {
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
    public boolean delete(Produit o) {
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
    public boolean update(Produit o) {
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
    public List<Produit> findAll() {
        List<Produit> produits = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            produits = session.createSelectionQuery("from Produit", Produit.class).getResultList();
            tx.commit();
            return produits;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return produits;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Produit findById(int id) {
        Produit produit = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            produit = session.get(Produit.class, id);
            tx.commit();
            return produit;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return produit;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Produit> getProductsByCategory(String categoryCode) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Produit p WHERE p.categorieId.code = :categoryCode";
            Query<Produit> query = session.createQuery(hql, Produit.class);
            query.setParameter("categoryCode", categoryCode);

            return query.list();
        }
    }

    public List<Produit> getProductsOrderedBetweenDates(Date startDate, Date endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM Produit p "
                    + "JOIN p.ligneCommandeProduitList l "
                    + "JOIN l.commande c "
                    + "WHERE c.dateCommande BETWEEN :startDate AND :endDate";

            Query<Produit> query = session.createQuery(hql, Produit.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);

            return query.list();
        }
    }

    public void getProductsByCommand(int commandeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CommandeService cs = new CommandeService();
            Commande cmd = cs.findById(commandeId);
            if (cmd == null) {
                System.out.println("command not found");
                return;
            }

            String hql = "SELECT l FROM LigneCommandeProduit l "
                    + "JOIN FETCH l.produit p "
                    + "WHERE l.commande.id = :commandId";

            Query<LigneCommandeProduit> query = session.createQuery(hql, LigneCommandeProduit.class);
            query.setParameter("commandId", commandeId);
            List<LigneCommandeProduit> ligneCommandes = query.getResultList();

            System.out.println("Commande : " + commandeId + "\t\tDate : " + cmd.getDateCommande());
            System.out.println("listes des produits : ");
            System.out.println("Reference\t\t\tPrix\t\t\tQuantite");
            ligneCommandes.forEach(ligne -> {
                System.out.println(ligne.getProduit().getReference() + "\t\t\t" + ligne.getProduit().getPrix() + "\t\t\t" + ligne.getQuantity());
            });

        }
    }

    public void findGreaterThen100() {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            session.createNamedQuery("Produit.findGreaterThen100", Produit.class).getResultList().forEach(produit -> {
                System.out.println(produit);
            });
        });
    }

}
