package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory=new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Student.class).
				buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try{
			Student tempStudent=new Student("Daffy","Duck","Daffy@luv2code.com");
			session.beginTransaction();
			session.save(tempStudent);
			session.getTransaction().commit();
			
			//to retrieve object using primary key
			
			// now get a new session and transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			// retrieve student based on the id: primary key
			List<Student> theStudent1=session.createQuery("from Student s where"+ "s.email LIKE '%luv2code.com'").list();
			for(Student i: theStudent1){
				System.out.println(i.getFirstName()+" "+i.getLastName()+" "+i.getId()+" "+i.getEmail());
			}
			session.getTransaction().commit();
			//commit the transaction
			
			//to Update object 
			
			// now get a new session key
			session = factory.getCurrentSession();
			session.beginTransaction();
			 int studentId = 6;
			 Student myStudent=session.get(Student.class, studentId);
			 session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			 //update first name to Scooby
			 //myStudent.setFirstName("Doggy");
			 // commit transaction
			 session.getTransaction().commit();
			}
		finally{
			factory.close();
		}


	}

}
