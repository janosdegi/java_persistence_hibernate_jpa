
package E_Mapping_Enums.client;

import E_Mapping_Enums.util.HibernateUtil;
import E_Mapping_Enums.entity.FilmTypeEnum;
import E_Mapping_Enums.entity.Movie2;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class HelloWorldClient {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();

			persist(session);

			txn.commit();
		}	catch(Exception e) {
				if(txn != null) { txn.rollback(); }
				e.printStackTrace();
		}	finally {
				if(session != null) { session.close(); }
		}
	
	}

	/**
	 *
	 * persisting the ManyToMany relationship between the Movie and Actor objects
	 *
	 * @param session
	 */
	private static void persist(Session session) {

		Movie2 movie = new Movie2("Star Wars", FilmTypeEnum.SCI_FI);
		session.persist(movie);
	}

}



































