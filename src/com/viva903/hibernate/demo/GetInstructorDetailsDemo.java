package com.viva903.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.viva903.hibernate.demo.entity.Instructor;
import com.viva903.hibernate.demo.entity.InstructorDetails;

public class GetInstructorDetailsDemo {

	public static void main(String[] args) {

//		create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).buildSessionFactory();
//		create session
		Session session = factory.getCurrentSession();

		try {
//			start a transaction
			session.beginTransaction();

//			get the instructor detail object
//			print the instructor detail
//			print the associated instructor
			InstructorDetails theInstructorDetails = session.get(InstructorDetails.class, 1);
			System.out.println("Instructor Details : " + theInstructorDetails);
			System.out.println("The associated instructor : " + theInstructorDetails.getInstructor());
			
//			commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
