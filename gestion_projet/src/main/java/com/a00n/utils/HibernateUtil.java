package com.a00n.utils;

import com.a00n.entities.EmployeTache;
import com.a00n.entities.EmployeTachePK;
import com.a00n.entities.Employee;
import com.a00n.entities.Projet;
import com.a00n.entities.Tache;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author ay0ub
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    // using the hibernate native apis
    static {
        if (sessionFactory == null) {
            final StandardServiceRegistry registry
                    = new StandardServiceRegistryBuilder()
                            .build();
            try {
                sessionFactory = new MetadataSources(registry)
                        //.addAnnotatedClass(Produit.class) // ex1
                        // ex 2
                        //                        .addAnnotatedClass(Categorie.class)
                        //                        .addAnnotatedClass(Produit.class)
                        //                        .addAnnotatedClass(Commande.class)
                        //                        .addAnnotatedClass(LigneCommandeProduit.class)
                        .addAnnotatedClass(Tache.class)
                        .addAnnotatedClass(Projet.class)
                        .addAnnotatedClass(Employee.class)
                        .addAnnotatedClass(EmployeTache.class)
                        .addAnnotatedClass(EmployeTachePK.class)
                        .buildMetadata()
                        .buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
