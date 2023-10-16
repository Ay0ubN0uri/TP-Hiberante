package com.a00n.utils;

import com.a00n.entities.Femme;
import com.a00n.entities.Homme;
import com.a00n.entities.Marriage;
import com.a00n.entities.MarriagePK;
import com.a00n.entities.Personne;
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
                        .addAnnotatedClass(Personne.class)
                        .addAnnotatedClass(Homme.class)
                        .addAnnotatedClass(Femme.class)
                        .addAnnotatedClass(Marriage.class)
                        .addAnnotatedClass(MarriagePK.class)
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
