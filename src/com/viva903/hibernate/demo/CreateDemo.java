package com.viva903.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.viva903.hibernate.demo.entity.Instructor;
import com.viva903.hibernate.demo.entity.InstructorDetails;

public class CreateDemo {

	public static void main(String[] args) {

//		create session factory
		SessionFactory factory = new Configuration()
															.configure("hibernate.cfg.xml")
															.addAnnotatedClass(Instructor.class)
															.addAnnotatedClass(InstructorDetails.class)
															.buildSessionFactory();
//		create session
		Session session = factory.getCurrentSession();

		try {
//			create the objects
//			Instructor tempInstructor = new Instructor("jin", "lee", "jinlee@viva903.com");
//			
//			InstructorDetails tempInstructorDetails = new InstructorDetails("http://www.viva903.com/youtube", "coding");
//			
			Instructor tempInstructor = new Instructor("Antony", "Santos", "antony@viva903.com");
			
			InstructorDetails tempInstructorDetails = new InstructorDetails("http://www.youtube.com", "soccer");
			
			
//			associate the objects 
			tempInstructor.setInstructorDetails(tempInstructorDetails);
			
//			start a transaction
			session.beginTransaction();

//			save the instructor
//			Due to Cacading type is all, it will automatically save both class
			session.save(tempInstructor);
			System.out.println(tempInstructor);
			
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
