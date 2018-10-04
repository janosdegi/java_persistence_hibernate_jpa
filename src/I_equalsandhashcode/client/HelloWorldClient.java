
package I_equalsandhashcode.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import I_equalsandhashcode.entity.Guide;
import I_equalsandhashcode.entity.Student;

import java.util.HashSet;
import java.util.Set;

public class HelloWorldClient {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("java_persistence_hibernate_jpa");
		
		//Guide with id=2L and Student with id=2L, both are being managed by the same EntityManager

//		EntityManager em1 = emf.createEntityManager();
//		em1.getTransaction().begin();
//
//		Student student = em1.find(Student.class, 32L);
//
//		em1.getTransaction().commit();
//		em1.close();
//
//		EntityManager em2 = emf.createEntityManager();
//		em2.getTransaction().begin();
//
//		Guide guide = em2.find(Guide.class, 31L);
//		Set<Student> students = guide.getStudents();
//
//		System.out.println(students.contains(student));
//
//		em2.getTransaction().commit();
//		em2.close();


		//Guide with id=2L and Student with id=2L, both are  being managed by the same EntityManager

		// 1. hashCode() and equals(Object obj) comment out in Students
		// 2. System.out.println(students2.contains(student2)); true or false ?
		//    => because there is no hashCode() and equals(Object obj) impl. in Student it should be false
		// 3. but! if the line (find student) executed the student with id 78 will be cached in the entity manager
		//    and manytoone relationship by default eagerly fetch the association (guide-id76 loaded and cached too)
		// 4. em.find(Guide, 76) finded and loaded from em-cache
		//    => student2 and the student associated with guide-id76 are the same java-level-object

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();   
		
		Student student2 = em.find(Student.class, 78L);
		
		Guide guide2 = em.find(Guide.class, 76L);
		Set<Student> students2 = guide2.getStudents();
		
		System.out.println(students2.contains(student2));

		em.getTransaction().commit();
		em.close();


//		equalsTest();
//		hashCodeTest();
	}

	/**
	 *
	 */
	private static void equalsTest() {
		Student student1 = new Student("2017TH10542", "Howard Wolowitz");
		Student student2 = new Student("2017TH10542", "Howard Wolowitz");
		if (student1.equals(student2)) {
			System.out.println("student1 and student2 are equals");
		} else {
			System.out.println("student1 and student2 are not equals");
		}
	}

	private static void hashCodeTest() {
		Student student1 = new Student("2017TH10542", "Howard Wolowitz");
		Student student2 = new Student("2017TH10542", "Howard Wolowitz");

		Set<Student> students = new HashSet<Student>();
		students.add(student1);
		if (students.contains(student2)) {
			System.out.println("students contains student2");
		} else {
			System.out.println("students does not contain student2");
		}

	}
}


































