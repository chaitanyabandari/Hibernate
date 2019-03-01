package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		SessionFactory factory1=new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Student.class).
				buildSessionFactory();
		
		Session session1=factory1.getCurrentSession();
		
		try{
			Student tempStudent1=new Student("rowdy","boy","paul@luv2code.com");
			Student tempStudent2=new Student("john","dave","john@luv2code.com");
			Student tempStudent3=new Student("maria","chaqra","maria@luv2code.com");
			session1.beginTransaction();
			session1.save(tempStudent1);
			session1.getTransaction().commit();
		}
		finally{
			factory1.close();
		}


	}

}
