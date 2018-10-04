
package F_Mapping_Collections_of_Basic_ValueTypes.client;

import F_Mapping_Collections_of_Basic_ValueTypes.entity.Address;
import F_Mapping_Collections_of_Basic_ValueTypes.entity.Friend;
import F_Mapping_Collections_of_Basic_ValueTypes.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class HelloWorldClient {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();

			// 1 persisting
			// alter table friend_nickname add primary key (friend_id, nickname)
			Friend friend = new Friend("Mark Anderson", "markanderson@gmail.com");

			friend.getNicknames().add("Marky");
			friend.getNicknames().add("Marco");
			friend.getNicknames().add("Markster");

			friend.getAddresses().add(new Address("street1","city1","zip1"));

			session.persist(friend);

			// 2 retrieving
//			Friend frnd = session.get(Friend.class, 91L);
//			System.out.println(frnd.toString());


			txn.commit();
		}	catch(Exception e) {
				if(txn != null) { txn.rollback(); }
				e.printStackTrace();
		}	finally {
				if(session != null) { session.close(); }
		}
	
	}



}



































