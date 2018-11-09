
package L_n_plus_1_Selects_Problem.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import L_n_plus_1_Selects_Problem.entity.Guide;
import L_n_plus_1_Selects_Problem.entity.Student;

public class HelloWorldClient {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-world");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		try {
			txn.begin();

			//-----------------------------------------------------------------------------------------------------
			// N+1 selects problem: by default single point associations (@OneToOne, @ManyToOne) are eagerly fetched
			// 1 select for all the parent objects (student)
			// 1 select for each child object
			// even the child objects are not wanted to fetch from db.
			// -> leads to bad performance
			// solution 1: parent: fetch = FetchType.LAZY
			//
			// but what if i need only the name attribute of all child object?
			// N+1 selects problem again.
			//
			// solution 2: writing jpql query to eagerly load the associated guide object:
			// "select student from Student student left join fetch student.guide"
			// the generated sql this time: only one select statement issued with a left outer join
			//
			//
			//-----------------------------------------------------------------------------------------------------

			//Adding data to guide and student table by adding a Guide and associating a Student with it
			Guide guide = new Guide("2000DO10777", "David Crow", 3000);		
			Student student = new Student("2014RG50347", "Rahul Singh");
			
			guide.addStudent(student);

			em.persist(guide);
	
			//Loading all the student objects
			/*
			Query query = em.createQuery("select student from Student student");
			List<Student> students = query.getResultList();	

			for (Student student : students) {
				System.out.println(student.getName() + ": " + student.getEnrollmentId());
			}  
			*/

			//Loading all the students with their associated Guide objects with the Student objects selectively (whenever you need to load them eagerly)
			/*
			Query query = em.createQuery("select student from Student student left join fetch student.guide");
			List<Student> students = query.getResultList();	

			for (Student student : students) {
				//students who do not have a guide will not be loaded
				if(student.getGuide() != null) {				
					System.out.println(student.getName() + ": " + student.getEnrollmentId() + ": " + student.getGuide().getName());
				}
			}  		
			*/

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














