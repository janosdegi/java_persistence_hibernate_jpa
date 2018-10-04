package G_Composite_Keys.client;

import org.hibernate.Session;

import G_Composite_Keys.util.HibernateUtil;
import G_Composite_Keys.entity.Chapter;
import G_Composite_Keys.entity.ChapterId;
import G_Composite_Keys.entity.Child;
import G_Composite_Keys.entity.Parent;
import G_Composite_Keys.entity.ParentPrimaryKey;
import G_Composite_Keys.entity.Section;

public class CompositeForeignKeyClient {
	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		//persisting		
		ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey("Charlotte", "Crawford");
		Parent parent = new Parent(parentPrimaryKey);
		
		Child child1 = new Child("Ruby");
		Child child2 = new Child("Groovy");
		
		parent.addChild(child1);
		parent.addChild(child2);
		
		session.persist(parent);
		
		session.getTransaction().commit();
		session.close();
		
	}
}
