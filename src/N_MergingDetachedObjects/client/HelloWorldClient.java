package N_MergingDetachedObjects.client;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import N_MergingDetachedObjects.entity.Guide;
import N_MergingDetachedObjects.entity.Student;

public class HelloWorldClient {	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-world");

		//=============================
		//Detached Objects
		//=============================
		EntityManager em1 = emf.createEntityManager();
		em1.getTransaction().begin();		
		
		Guide guide = em1.find(Guide.class, 2L);		
		Set<Student> students = guide.getStudents();	// because of the FetchType.LAZY az this point
														// students associated with guide has only a proxy in the
														// first level cache (EM). students objects are not yet loaded.

		int numOfStudents = students.size();			// by calling a collection management operator will be all of
														// the students proxies initialized
		
		Student student = null;
		for(Student nextStudent: students) {
			if(nextStudent.getId() == 2L) {
				student = nextStudent;
			}
		}
		
		em1.getTransaction().commit();
		em1.close();	// all the objects being managed by it will become a detached objects

		guide.setSalary(2500); // modifying the detached objects
		student.setName("Amy Jade Gill");
		
		EntityManager em2 = emf.createEntityManager();
		em2.getTransaction().begin();
		
		@SuppressWarnings("unused")
		Guide mergedGuide = em2.merge(guide); // the detached objects will be managed by em2
		
		em2.getTransaction().commit(); 	// dirty check => guide.salary (id=2) will be modified to 2500 and committed
										// the name of the student (id=1) still remains "Amy Gill" (! unchanged)
										// the modified name of the student will be not automatically copied
										// only in case of CascadeType.MERGE
		em2.close();		

		//=============================
		//Extended Persistence Context
		//=============================
		/*
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Guide guide = em.find(Guide.class, 2L);
		Set<Student> students = guide.getStudents();
		int numOfStudents = students.size();

		Student student = null;
		for(Student nextStudent: students) {
			if(nextStudent.getId() == 1L) {
				student = nextStudent;
			}
		}

		em.getTransaction().commit();

		guide.setSalary(2500);
		student.setName("Amy Jade Gill");

		em.getTransaction().begin();

		//merging not needed

		em.getTransaction().commit();

		em.close();
		*/	
	}
}