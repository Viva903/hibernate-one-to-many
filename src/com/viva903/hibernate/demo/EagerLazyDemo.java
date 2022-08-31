package com.viva903.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.viva903.hibernate.demo.entity.Course;
import com.viva903.hibernate.demo.entity.Instructor;
import com.viva903.hibernate.demo.entity.InstructorDetails;

public class EagerLazyDemo {

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
			int theIntructorId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theIntructorId);

			System.out.println("\nviva903 => Instructor : " + tempInstructor + "\n");

//			fetch type : lazy, only retrieve courses on demand 
			System.out.println("\nviva903 => Courses Associated : " + tempInstructor.getCourses() + "\n");

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
