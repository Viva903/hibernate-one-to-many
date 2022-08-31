package com.viva903.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.viva903.hibernate.demo.entity.Course;
import com.viva903.hibernate.demo.entity.Instructor;
import com.viva903.hibernate.demo.entity.InstructorDetails;

public class FetchJoinDemo {

	public static void main(String[] args) {

//		create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class).buildSessionFactory();
//		create session
		Session session = factory.getCurrentSession();

		try {
//			start a transaction
			session.beginTransaction();

//			get instructor from db
			int theId = 1;
//			Instructor tempInstructor = session.get(Instructor.class, theIntructorId);

//			Load all data at once using HQL
			Query<Instructor> query = session.createQuery("select i from Instructor i "
															+ "JOIN FETCH i.courses "
															+ "where i.id=:theInstructorId", 
					Instructor.class);
			
//			set parameter on query
			query.setParameter("theInstructorId", theId);
//			execute equery and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("\nviva903 => Instructor : " + tempInstructor + "\n");

//			fetch type : lazy, only retrieve courses on demand 
//			System.out.println("\nviva903 => Courses Associated : " + tempInstructor.getCourses() + "\n");

//			commit transaction
			session.getTransaction().commit();

//			Add this to test lazy loading after session closed
			System.out.println("\nThe session is now closed!\n");
			session.close();
			System.out.println("\nviva903 => Courses Associated : " + tempInstructor.getCourses()+ "\n");

			System.out.println("\nviva903 => Done!"+ "\n");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
