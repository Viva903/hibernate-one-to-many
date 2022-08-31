package com.viva903.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.viva903.hibernate.demo.entity.Instructor;
import com.viva903.hibernate.demo.entity.InstructorDetails;

public class DeleteDemo {

	public static void main(String[] args) {

//		create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).buildSessionFactory();
//		create session
		Session session = factory.getCurrentSession();

		try {
//			start a transaction
			session.beginTransaction();

//			get instructor by primary key / id
			Instructor tempInstructor = session.get(Instructor.class, 2);
			System.out.println("Found instructor : " + tempInstructor);

//			delete the instructors
			if (tempInstructor != null) {
				System.out.println("Deleting: " + tempInstructor);
				session.delete(tempInstructor);
			}

//			commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

}
