package B_one_to_many_relationship.client;

import B_one_to_many_relationship.entity.Guide;
import B_one_to_many_relationship.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import B_one_to_many_relationship.util.HibernateUtil;


public class HelloWorldClient {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();

//			persist(session);
//			updateInverseEnd(session);
//			updateOwner(session);
			updateInverseEnd2(session);

			txn.commit();
		}	catch(Exception e) {
			if(txn != null) { txn.rollback(); }
			e.printStackTrace();
		}	finally {
			if(session != null) { session.close(); }
		}


	}

	private static void persist(Session session) {
		Guide guide1 = new Guide("2000MO10999", "Mike Lawson", 1000);
		Guide guide2 = new Guide("2000IM10888", "Ian Lamb", 2000);

		Student student1 = new Student("2014JT50428", "John Smith", guide1);
		Student student2 = new Student("2014AL50379", "Amy Gill", guide1);

//		1. persist owner
//		session.persist(student1);
//		session.persist(student2);

//		2. persist inverse end
		guide1.getStudents().add(student1);
		guide1.getStudents().add(student2);

		session.persist(guide1);
		session.persist(guide2);

	}

	private static void updateInverseEnd(Session session) {
		Guide guide = (Guide) session.get(Guide.class, 79L);
		guide.setSalary(2300);
		Student student = (Student) session.get(Student.class, 78L);
		guide.getStudents().add(student);
	}

	private static void updateOwner(Session session) {
		Guide guide = (Guide) session.get(Guide.class, 79L);
		Student student = (Student) session.get(Student.class, 78L);
		student.setGuide(guide);
	}

	private static void updateInverseEnd2(Session session) {
		//Updating inverse end (after adding addStudent(Student) in Guide entity)
		Guide guide = (Guide) session.get(Guide.class, 76L);
		Student student = (Student) session.get(Student.class, 78L);
		guide.addStudent(student);
	}

}

