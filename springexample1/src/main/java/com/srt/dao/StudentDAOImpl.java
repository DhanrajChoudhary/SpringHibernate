package com.srt.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.srt.model.Student;

public class StudentDAOImpl implements StudentDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void insert(Student student) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(student);
		transaction.commit();
		session.close();
	}

	public void delete(Student student) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(student);
		transaction.commit();
		session.close();
		
	}

	public Student getValueforID(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		return (Student) session.get(Student.class, id);
		
	}
	public void update(Student student) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.update(student);
		transaction.commit();
		session.close();
	}

	

}