
package H_jpa__hibernate_as_jpa_provider.client;

import H_jpa__hibernate_as_jpa_provider.entity.Guide;
import H_jpa__hibernate_as_jpa_provider.entity.Message;
import H_jpa__hibernate_as_jpa_provider.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Set;


public class HelloWorldClient {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("java_persistence_hibernate_jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		try {
			txn.begin();

			hibernateAsJPAProvider(em);

//			workingWithObjects(em,txn,emf);

//			fetchStrategies();

			txn.commit();
		}	catch(Exception e) {
				if(txn != null) { txn.rollback(); }
				e.printStackTrace();
		}	finally {
				if(em != null) { em.close(); }
		}
        
	}

	/**
	 *
	 * @param em
	 */
	private static void hibernateAsJPAProvider(EntityManager em) {
		Message msg= new Message("Hello World with Hibernate as JPA Provider 11.09.2018");
		em.persist(msg);
	}

	/**
	 *
	 * @param em
	 * @param txn
	 * @param emf
	 */
	private static void workingWithObjects(EntityManager em, EntityTransaction txn, EntityManagerFactory emf) {

		Message message = new Message("Hello JPA - Working With objects 11.09.2018"); //transient state
		em.persist(message); //persistent state

		txn.commit();
		em.close();

		message.setText("Hi JPA - Working With objects"); //modifying the detached state of message

		EntityManager em2 = emf.createEntityManager();
		EntityTransaction txn2 = em2.getTransaction();
		txn2.begin();

		//the returned mergedMessage is a persistent object
		//any changes to mergedMessage will be dirty checked when the txn2 will be committed and updated in the database
		Message mergedMessage = em2.merge(message);

		em2.detach(message);

		txn2.commit();
		em2.close();


		//Detaching objects explicitly

		EntityManager em3 = emf.createEntityManager();
		EntityTransaction txn3 = em3.getTransaction();
		txn3.begin();

		Message msg = new Message("Howdy"); //transient state
		em3.persist(msg); //persistent state

		//em3.detach(msg); //detaching the message object explicitly
		txn3.commit();
		em3.close();



	}

	/**
	 *
	 */
	private static void fetchStrategies() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("java_persistence_hibernate_jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();

		Guide guide = null;
		Set<Student> students = null;
		try {
			txn.begin();

			guide = em.find(Guide.class, 31L);
			students = guide.getStudents();

			if (students == null) // at that pint will be all students loaded when the collection of the students will be used
				System.out.println("students is null");

			//int numberOfStudents = students.size();
			// Lazy fetching, numberOfStudents get the number of Students which connected to guide object

			txn.commit();
		} catch(Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		//outside of the scope of em  (em is closed -> em.close()) lazy fetchnig/loading does not work anymore ->
		int numberOfStudents = students.size(); //org.hibernate.LazyInitializationException:
												// failed to lazily initialize a collection of role:
												// jpa__hibernate_as_jpa_provider.Guide.students, could not initialize proxy
		System.out.println(numberOfStudents);
	}
}














