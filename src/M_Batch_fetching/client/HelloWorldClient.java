
package M_Batch_fetching.client;

import M_Batch_fetching.entity.Guide;
import M_Batch_fetching.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloWorldClient {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-world");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		try {
			txn.begin();

			//-----------------------------------------------------------------------------------------------------
			// When we got many 1000 of students maybe we need the strategy Batch fetching.
			// It is an alternate solution for the N+1 Select problem.
			//
			// we have 10.000 students and each of them has a guide for it.
			// -> fetch = FetchType.LAZY
			// only the students entities (10.000) will be loaded in the first leve cache (Entity Manager).
			// what if we need to print the name of the guide as well?
			//
			// -> student.getGuide().getName()
			// the proxy of the guide object will be initialized in the first leve cache (Entity Manager).
			// and by all of the guide associated to the sudent objects will be generated a select statement.
			// in total: 10.000 extra select statement -> bad performance!
			// Guide annotated with @BatchSize(size=4)
			// it is taking just one select statement to load four guides an once
			// -> 4 proxy will be initialized at once -> 1 select statement instead of 4
			// (2500 instead of 10.000 exta select statement)
			//
			//
			//
			//
			//
			//-----------------------------------------------------------------------------------------------------



			txn.commit();			
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}
}














