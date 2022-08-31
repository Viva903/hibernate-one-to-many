package com.viva903.hibernate.dev;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.viva903.hibernate.demo.entity.Employee;

public class CreateEmployeePractice {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
//			Create object to save into db
			session.beginTransaction();
			System.out.println("Start saving 5 employees data");
			Employee employee1 = new Employee("Santos", "Antony", "Ajax");
			Employee employee2 = new Employee("Lisandro", "Martinez", "Manchester");
			Employee employee3 = new Employee("Tyrell", "Malacia", "Feyenoord");
			Employee employee4 = new Employee("Christian", "Eriksen", "Manchester");
			Employee employee5 = new Employee("Henrique", "Casemiro", "Madrid");

			session.save(employee1);
			session.save(employee2);
			session.save(employee3);
			session.save(employee4);
			session.save(employee5);
			
			session.getTransaction().commit();
			System.out.println("Done storing 5 employees data to database");
			
//			retrieve an object by primary key
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("Retrieve an object by primary key");
			
			int employeeId = 2;
			System.out.println("Employee id : " + employeeId);
			
			Employee theEmployee = session.get(Employee.class, employeeId);
			System.out.println("Get complete Employee by primary Key: " + theEmployee);
			
			session.getTransaction().commit();
			System.out.println("Done retrieve an object by primary key");
			
//			Query objects to find employees for a given company
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("Query to find employess for a given company ");
			
			List<Employee> employeeList = session.createQuery("from Employee s where s.company='Manchester'").getResultList();
			for (Employee tempEmployee : employeeList) {
				System.out.println(tempEmployee);
			}
			
			session.getTransaction().commit();
			System.out.println("Done query objects to find employees for a given company");
			
//			Delete an object by primary key
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("Delete an object by primary key ");
			
			session.createQuery("delete from Employee where id=5").executeUpdate();
			
			
			session.getTransaction().commit();
			System.out.println("Done deleting an object by primary key");
			
//			Update object id = 3 where company = Manchester
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("Updating object id = 3, company = Manchester");
			
			theEmployee = session.get(Employee.class, 3);
			theEmployee.setCompany("Manchester");
			
			session.getTransaction().commit();
			System.out.println("Done Updating id = 3");
		
		} finally {
			factory.close();
		}

	}

}
