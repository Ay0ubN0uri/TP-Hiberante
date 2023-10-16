package com.a00n.utils;

import com.a00n.entities.Categorie;
import com.a00n.entities.Commande;
import com.a00n.entities.LigneCommandeProduit;
import com.a00n.entities.Produit;
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
                        // ex 2
                        .addAnnotatedClass(Categorie.class)
                        .addAnnotatedClass(Produit.class)
                        .addAnnotatedClass(Commande.class)
                        .addAnnotatedClass(LigneCommandeProduit.class)
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
