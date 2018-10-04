package A_firstlesson.client;

import A_firstlesson.entity1.*;
import A_firstlesson.entity1.Student;
import org.hibernate.Session;

import org.hibernate.Transaction;
import A_firstlesson.util.HibernateUtil;


public class HelloWorldClient {

	public static void main(String[] args) {

//		1.
//		First lesson
//		firstLesson();


//		2.
//		Mapping Concepts

//		2.1
//		Entity - Value Object, Person, Message (Embeddable)
//		componentMapping();


//		3.
//		Mapping Assosiations (f.ex. ManyToOne; Cascade)
		mappingAssociations();


	}

	private static void firstLesson() {
		//		FIRST LESSON ------------------------------------------------------------------------------

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();


//		1. -------------------------------------
//		Persist an Object with hibernate session
//		------------------------------------------------------------------------------

//		1.1 hbm.xml
//		firstlesson.domain.Message message = new firstlesson.domain.Message( "Hello World with Hibernate & JPA Annotations - JPA 4 22.03 12:37" );

//		1.2 annotation
//		Message message = new Message( "Hello World with Hibernate & JPA Annotations - JPA 4 22.03 13:43" );

//		session.save(message);


//		2.  -----------------
//		Getting a Message obj
//		------------------------------------------------------------------------------

//		Message msg = (Message)session.get(Message.class, 63L);
//		System.out.println("---------------------------------------------");
//      System.out.println(msg.getText());
//      System.out.println("---------------------------------------------");


//		3. -------------------------
//		- Automatic Dirty Checking -
//		------------------------------------------------------------------------------
//		Getting the object, changing one of the attributes of the object (f.ex. message.text)
//		and without saving, hibernate executes automatically an update statement of this "changed/dirty" object
//		Message msg = (Message)session.get(Message.class, 61L);
//		msg.setText("Hello Automatic Dirty Checking (2)!");



//		4. --------
//		Delete
//		------------------------------------------------------------------------------
//		Message msg = (Message)session.get(Message.class, 61L);
//		session.delete(msg);
//		the msg object in this line exist both in the memory (heap) and tha database
//		only by Transaction().commit() will be deleted

		session.getTransaction().commit();
		session.close();
//		-------------------------------------------------------------------------------------------
	}

	/**
	 * Entity - Value Object, Person-Address (Embeddable)
	 * (more classes then tables)
	 * "a component is a part of a whole in such a way that if the whole is destroyed,
	 * all its parts are also destroyed width it"
	 *
	 */
	private static void componentMapping() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();

//			Address is a component of the whole (Person) and has no individual identity
			Address address = new Address("Alserbach Strasse", "Wien", "1090");
			Person person = new Person("John", address);


//			AttributeOverrides ---------------------------------------------------------------

//			Address homeAddress = new Address("200 E Main St", "Seattle", "85123");
//			Address billingAddress = new Address("250 E Main St", "Detroit", "76553");
//			Person3 person = new Person3("Ruby", homeAddress, billingAddress);
// 			-----------------------------------------------------------------------------------


			session.save(person);

			txn.commit();
		}	catch(Exception e) {
			if(txn != null) { txn.rollback(); }
			e.printStackTrace();
		}	finally {
			if(session != null) { session.close(); }
		}
	}


	/**
	 *  ManyToOne, Cascade
	 *
	 *
	 */
	private static void mappingAssociations() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();

//			PESIST -----------------------------------------------------------------------------
			persistStudentAndGuide(session);

//			DELETE -----------------------------------------------------------------------------
//			deleteStudentAndGuide(session);

			txn.commit();
		}	catch(Exception e) {
			if(txn != null) { txn.rollback(); }
			e.printStackTrace();
		}	finally {
			if(session != null) { session.close(); }
		}
	}

	/**
	 *
	 * @param session
	 */
	private static void persistStudentAndGuide(Session session) {

//		PESIST -----------------------------------------------------------------------------
		Guide guide = new Guide("2000MO10456", "Mike Lawson", 1000);
		Student student = new Student("2014JT50972", "John Smith", guide);

//		1. without cascade
//		session.save(guide);
//		session.save(student);

//		2. with cascade
		session.persist(student);
//		------------------------------------------------------------------------------------
	}

	private static void deleteStudentAndGuide(Session session) {
//		DELETE -----------------------------------------------------------------------------
		Student student2 = session.get(Student.class, 65L);
		session.delete(student2);
//		------------------------------------------------------------------------------------
	}



}

