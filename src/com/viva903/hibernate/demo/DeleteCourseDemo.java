package com.viva903.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.viva903.hibernate.demo.entity.Course;
import com.viva903.hibernate.demo.entity.Instructor;
import com.viva903.hibernate.demo.entity.InstructorDetails;

public class DeleteCourseDemo {

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

//			get a course from db
			int theCourseId = 10;
			Course tempCourse = session.get(Course.class, theCourseId);
//			delete course
			System.out.println("Deleting the course : " + tempCourse);
			session.delete(tempCourse);
			
			
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
