package G_Composite_Keys.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
	
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory() {
        try {        	
        	// Create the SessionFactory from hibernate.cfg.xml
//            File file = new File("d:\\workspace_jpa\\java_persistence_hibernate_jpa\\src\\G_Composite_Keys\\util\\hibernate.cfg.xml");
//        	Configuration configuration = new Configuration().configure(file);
//
//            System.out.println("-----------------------");
//        	System.out.println(configuration.getProperty("connection.driver_class"));
//        	System.out.println(configuration.getProperty("connection.driver_class"));
//            System.out.println("-----------------------");

//        	Configuration configuration = new Configuration().configure("/G_Composite_Keys/util/hibernate.cfg.xml");
//            return configuration.buildSessionFactory( new StandardServiceRegistryBuilder().applySettings( configuration.getProperties() ).build() );

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("/G_Composite_Keys/util/hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


	
}


