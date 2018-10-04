package Optimistic_Locking_and_Versioning.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Optimistic_Locking_and_Versioning.entity.Guide;

public class User2Client {	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("java_persistence_hibernate_jpa");
		EntityManager em1 = emf.createEntityManager();
		em1.getTransaction().begin();		
		
		Guide guide = em1.find(Guide.class, 31L);
	
		em1.getTransaction().commit();
		em1.close();	

		guide.setSalary(4000);
		
		EntityManager em2 = emf.createEntityManager();
		em2.getTransaction().begin();
		
		Guide mergedGuide = em2.merge(guide);
		
		em2.getTransaction().commit();
		em2.close();	
		
	}
}
