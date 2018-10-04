package G_Composite_Keys.client;

import org.hibernate.Session;

import G_Composite_Keys.util.HibernateUtil;
import G_Composite_Keys.entity.Chapter;
import G_Composite_Keys.entity.ChapterId;
import G_Composite_Keys.entity.Parent;
import G_Composite_Keys.entity.ParentPrimaryKey;

public class CompositePrimaryKeyClient {
	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		//persisting		
		ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey("Gavin", "King");
		Parent parent = new Parent(parentPrimaryKey);
		session.persist(parent);
		
		//retrieving	
		/*
		ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey("Gavin", "King");
		Parent parent = (Parent) session.get(Parent.class, parentPrimaryKey);	
		*/		
		
		session.getTransaction().commit();
		session.close();
		
	}
}
