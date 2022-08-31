package com.viva903.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.viva903.hibernate.demo.entity.Course;
import com.viva903.hibernate.demo.entity.Instructor;
import com.viva903.hibernate.demo.entity.InstructorDetails;
import com.viva903.hibernate.demo.entity.Review;

public class CreateCoursesAndReviewDemo {

	public static void main(String[] args) {

//		create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();
//		create session
		Session session = factory.getCurrentSession();

		try {
//			start a transaction
			session.beginTransaction();

//			create a course
			Course tempCourse = new Course("Pacman - How To Score One Million Points");

//			add some reviews
			tempCourse.addReview(new Review("Wonderful course !! "));
			tempCourse.addReview(new Review("Awesome course !! "));
			tempCourse.addReview(new Review("Mehhhhh course !! "));
			tempCourse.addReview(new Review("Cool course !! "));

//			save the course and leverage the cascade all
			System.out.println("\nViva903 => Saving course with reviews.... \n");
			System.out.println("\nViva903 => " + tempCourse + "\n");
			System.out.println("\nViva903 => " + tempCourse.getReviews() + "\n");
			session.save(tempCourse);

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
