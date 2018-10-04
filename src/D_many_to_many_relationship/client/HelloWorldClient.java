
package D_many_to_many_relationship.client;

import D_many_to_many_relationship.entity.Actor;
import D_many_to_many_relationship.entity.Movie;
import org.hibernate.Session;
import org.hibernate.Transaction;

import D_many_to_many_relationship.util.HibernateUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class HelloWorldClient {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();

//			persist(session);

//			printMovies((Actor)session.get(Actor.class, 84L),
//					(Actor)session.get(Actor.class, 86L),
//					(Actor)session.get(Actor.class, 88L),
//					(Actor)session.get(Actor.class, 89L));

			printActors((Movie)session.get(Movie.class, 85L));

//			updateInverseEnd(session);

//			updateRelationships(session);


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
	 * persisting the ManyToMany relationship between the Movie and Actor objects
	 *
	 * @param session
	 */
	private static void persist(Session session) {

		Movie movie1 = new Movie("American Hustle");
		Movie movie2 = new Movie("The Prestige");
		Movie movie3 = new Movie("The Dark Knight Rises");

		Actor actor1 = new Actor("Christian Bale");
		Actor actor2 = new Actor("Hugh Jackman");
		Actor actor3 = new Actor("Tom Hardy");
		Actor actor4 = new Actor("Ban Afflack");

		movie1.getActors().add(actor1);

		movie2.getActors().add(actor1);
		movie2.getActors().add(actor2);

		movie3.getActors().add(actor1);
		movie3.getActors().add(actor3);

		session.persist(movie1);
		session.persist(movie2);
		session.persist(movie3);
		session.persist(actor4);
	}

	/**
	 * Print movies of the Actors
	 *
	 * @param actor1
	 * @param actor2
	 * @param actor3
	 */
	private static void printMovies(Actor actor1, Actor actor2, Actor actor3, Actor actor4) {

		System.out.println(actor1.getName());
		for (Movie movie : (actor1.getMovies() != null ? actor1.getMovies() : Collections.<Movie>emptyList())) {
			System.out.println(movie.getName());
		}

		System.out.println("");
		System.out.println(actor2.getName());
		for (Movie movie : (actor2.getMovies() != null ? actor2.getMovies() : Collections.<Movie>emptyList())) {
			System.out.println(movie.getName());
		}

		System.out.println("");
		System.out.println(actor3.getName());
		for (Movie movie : (actor3.getMovies() != null ? actor3.getMovies() : Collections.<Movie>emptyList())) {
			System.out.println(movie.getName());
		}

		System.out.println("");
		System.out.println(actor4.getName());
		for (Movie movie : (actor4.getMovies() != null ? actor4.getMovies() : Collections.<Movie>emptyList())) {
			System.out.println(movie.getName());
		}
	}

	/**
	 *
	 * @param movie
	 */
	private static void printActors(Movie movie) {
		System.out.println(movie.getName());
		for (Actor actor : (movie.getActors() != null ? movie.getActors() : Collections.<Actor>emptyList())) {
			System.out.println(actor.getName());
		}
	}

	/**
	 *
	 * Updating the inverse end (Actor)
	 *
	 * @param session
	 */
	private static void updateInverseEnd(Session session) {

		Movie movie = (Movie) session.get(Movie.class, 37L);
		Actor actor = (Actor) session.get(Actor.class, 42L);
		actor.getMovies().add(movie);
	}

	/**
	 *
	 * Updating the owner (Movie)
	 *
	 * @param session
	 */
	private static void updateOwner(Session session) {
		Movie movie = (Movie) session.get(Movie.class, 1L);
		Actor actor = (Actor) session.get(Actor.class, 2L);
		movie.getActors().add(actor);
	}

	/**
	 *
	 * @param session
	 */
	private static void updateRelationships(Session session) {
		Movie movie = (Movie) session.get(Movie.class, 48L);

		Actor actor1 = (Actor) session.get(Actor.class, 50L);

		Set removableActors = new HashSet<Actor>();
		for (Actor actor : (movie.getActors() != null ? movie.getActors() : Collections.<Actor>emptyList())) {
			removableActors.add(actor);
		}

		movie.getActors().removeAll(removableActors);

		movie.getActors().add(actor1);

	}
}



































