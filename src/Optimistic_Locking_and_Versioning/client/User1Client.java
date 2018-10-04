package Optimistic_Locking_and_Versioning.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;

import Optimistic_Locking_and_Versioning.entity.Guide;

public class User1Client {	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("java_persistence_hibernate_jpa");
		EntityManager em1 = emf.createEntityManager();
		em1.getTransaction().begin();		
		
		Guide guide = em1.find(Guide.class, 31L);
	
		em1.getTransaction().commit();
		em1.close();	

		guide.setSalary(3000);
		
		EntityManager em2 = emf.createEntityManager();
		EntityTransaction txn2 = em2.getTransaction();
		try {
			txn2.begin();
			
			Guide mergedGuide = em2.merge(guide);
			
			txn2.commit();
		} catch (OptimisticLockException ole) {
			if(txn2 != null) { 
				txn2.rollback(); 
				System.err.println("The guide was updated by some other user while you were doing interesting things.");
			}
			ole.printStackTrace();
		} finally {
			if(em2 != null) { em2.close(); }
		}	
	}
}
