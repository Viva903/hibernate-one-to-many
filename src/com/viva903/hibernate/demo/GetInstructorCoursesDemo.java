package com.viva903.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.viva903.hibernate.demo.entity.Course;
import com.viva903.hibernate.demo.entity.Instructor;
import com.viva903.hibernate.demo.entity.InstructorDetails;

public class GetInstructorCoursesDemo {

	public static void main(String[] args) {

//		create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetails.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
//		create session
		Session session = factory.getCurrentSession();

		try {
//			start a transaction
			session.beginTransaction();
			
//			get instructor from db
			int theIntructorId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theIntructorId);
			
			System.out.println("Instructor : " + tempInstructor);
			System.out.println("Courses Associated : " + tempInstructor.getCourses());
			
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
