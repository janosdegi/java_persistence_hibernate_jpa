
package C_one_to_one_relationship.client;

import C_one_to_one_relationship.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import C_one_to_one_relationship.entity.Customer;
import C_one_to_one_relationship.entity.Passport;


public class HelloWorldClient {
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();

			Passport passport = new Passport("925076473");
			Customer customer = new Customer("Nicole Scott 2", passport);

			session.persist(customer);

			txn.commit();
		}	catch(Exception e) {
				if(txn != null) { txn.rollback(); }
				e.printStackTrace();
		}	finally {
				if(session != null) { session.close(); }
		}
	
	}
}












