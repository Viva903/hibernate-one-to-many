package com.viva903.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.viva903.hibernate.demo.entity.Course;
import com.viva903.hibernate.demo.entity.Instructor;
import com.viva903.hibernate.demo.entity.InstructorDetails;

public class CreateCoursesDemo {

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
//			create some courses
			Course tempCourse1 = new Course("Anti Mage - The Ultimate Guide Dota 2");
			Course tempCourse2 = new Course("Football Manager 2022 Master Tactician");
			Course tempCourse3 = new Course("Shopee App - Gaming Blueprint");
			
//			add courses to instructor
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			tempInstructor.add(tempCourse3);
			
//			save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.save(tempCourse3);
			
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
