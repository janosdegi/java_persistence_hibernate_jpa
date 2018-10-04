
package J_querylanguage.client;

import J_querylanguage.entity.Guide;
import J_querylanguage.entity.Student;

import javax.persistence.*;
import java.util.List;

public class HelloWorldClient {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("java_persistence_hibernate_jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		try {
			txn.begin();

			//Querying Entities
			/*
			Query query = em.createQuery("select guide from J_querylanguage.entity.Guide guide");
			List<Guide> guides = query.getResultList();
			for (Guide guide : guides) {
				System.out.println(guide);
			}   
			*/

			/*
			Query query = em.createQuery("select guide.name from J_querylanguage.entity.Guide guide");
			List<String> names = query.getResultList();
			for (String name : names) {
				System.out.println(name);
			}
			*/
	
			//############################


			//Reporting Queries
			/*
			Query query = em.createQuery("select guide.name, guide.salary from J_querylanguage.entity.Guide guide");
			List<Object[]> resultList = query.getResultList();
			for (Object[] objects : resultList) {
				System.out.println("Object[] {objects[0]: " + objects[0] + ", objects[1]: " + objects[1] + "}");				
			}
			*/
	
			//############################
	
	
			//Dynamic Queries
			/*
			String name = "Ian Lamb"; //simulating dynamic query
			Query query = em.createQuery("select guide from J_querylanguage.entity.Guide guide where guide.name = '" + name + "'");
			Guide guide = (Guide) query.getSingleResult();
			System.out.println(guide);
			*/

			/*
			Query query = em.createQuery("select guide from J_querylanguage.entity.Guide guide where guide.name = :name");
			query.setParameter("name", "Ian Lamb");
			Guide guide = (Guide) query.getSingleResult();
			System.out.println(guide);
			*/
	
			//############################
	
	
			//Chaining Method Calls
			/*
			Guide guide = (Guide) em.createQuery("select guide from J_querylanguage.entity.Guide guide where guide.name = :name")
					        .setParameter("name", "Ian Lamb")
					        .getSingleResult();
			System.out.println(guide);
			*/
	
			//############################
	
	
			//Wildcards
			/*
			Query query = em.createQuery("select guide from J_querylanguage.entity.Guide guide where guide.name like 'm%'");
			List<Guide> guides = query.getResultList();
			for (Guide guide : guides) {
				System.out.println(guide);
			}
			*/
	
			//############################
	

			//Native SQL Queries
			/*
			Query query = em.createNativeQuery("select * from Guide", Guide.class);
			List<Guide> guides = query.getResultList();
			for (Guide guide : guides) {
				System.out.println(guide);
			}
			*/
	
			//############################	

			//Named Queries
			/*
			List<J_querylanguage.entity.Guide> guides = em.createNamedQuery("findByGuide")
					       						.setParameter("name", "Mike Lawson")
					       						.getResultList();
			for (J_querylanguage.entity.Guide guide : guides) {
				System.out.println(guide);
			}
			*/

			/*
			int numOfGuides = em.createQuery("select guide from J_querylanguage.entity.Guide guide").getResultList().size();
			System.out.println("[numOfGuides: " + numOfGuides + "]");
			*/
	
			//############################
	

			//Aggregate Functions
			/*
			Query query = em.createQuery("select count(guide) from J_querylanguage.entity.Guide guide");
			long numOfGuides = (long) query.getSingleResult();
			System.out.println("[numOfGuides: " + numOfGuides + "]");
			*/

			/*
			Query query = em.createQuery("select max(guide.salary) from J_querylanguage.entity.Guide guide");
			Integer maximumSalary = (Integer) query.getSingleResult();
			System.out.println("[maximumSalary: " + maximumSalary + "]");        	
			*/
	
			/*
			Query query = em.createQuery("select guide from J_querylanguage.entity.Guide guide where guide.salary = 1000");
			List<Guide> guides = query.getResultList();
			for (Guide guide : guides) {
				System.out.println(guide);
			}     	
			*/
	
			//############################	
	
			//Persisting a Student object
			/*
			Student student = new Student("2014BE50789", "Bruce Lee");
			em.persist(student);
			*/
	
			//############################	
	
			//Joining Associations
			/*
			Query query = em.createQuery("select student from J_querylanguage.entity.Student student join student.guide guide");
			List<Student> students = query.getResultList();
			for (Student student : students) {
				System.out.println(student);
			}
			*/
	
			/*
			Query query = em.createQuery("select student from J_querylanguage.entity.Student student left join student.guide guide");
			List<Student> students = query.getResultList();
			for (Student student : students) {
				System.out.println(student);
			}
			*/
	
			/*
			Query query = em.createQuery("select student from J_querylanguage.entity.Student student right join student.guide guide");
			List<Student> students = query.getResultList();
			for (Student student : students) {
				System.out.println(student);
			}
			*/
	
			//############################

	
			//Fetching Associations
			/*
			Query query = em.createQuery("select guide from J_querylanguage.entity.Guide guide join guide.students student");
			List<Guide> guides = query.getResultList();
			*/
	
			/*
			Query query = em.createQuery("select guide from J_querylanguage.entity.Guide guide join fetch guide.students student");
			List<Guide> guides = query.getResultList();
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














