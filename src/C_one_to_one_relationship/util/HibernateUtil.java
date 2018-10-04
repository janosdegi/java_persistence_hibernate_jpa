
package C_one_to_one_relationship.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory() {
        try {        	
            // for Hibernate 4.3.x users
            // Create the SessionFactory from hibernate.cfg.xml 
//            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
//            return configuration.buildSessionFactory(
//                    new StandardServiceRegistryBuilder().applySettings( configuration.getProperties() ).build() );

            // for Hibernate 5.x users
            // Create the SessionFactory from hibernate.cfg.xml

//            InputStream in = new HibernateUtil().getClass().getResourceAsStream("/firstlesson/util/hibernate.cfg.xml");
//
//            URL url = new HibernateUtil().getClass().getResource("/firstlesson/util/hibernate.cfg.xml");
//
//            System.out.println("---------------------------------------------");
//            System.out.println("url.getPath(): " + url.getPath());
//            System.out.println("---------------------------------------------");

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("/C_one_to_one_relationship/util/hibernate.cfg.xml").build();
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



