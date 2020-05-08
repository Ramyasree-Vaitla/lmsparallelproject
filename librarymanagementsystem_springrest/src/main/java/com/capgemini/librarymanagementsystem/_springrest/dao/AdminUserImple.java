package com.capgemini.librarymanagementsystem._springrest.dao;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagementsystem_springrest.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.UsersInfoBean;
import com.capgemini.librarymanagementsystem_springrest.exceptions.LMSException;

@Repository
public class AdminUserImple implements AdminUserDao {

	@PersistenceUnit
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean register(UsersInfoBean user) {
		// TODO Auto-generated method stub
		boolean isRegistered = false;
		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(user);
			transaction.commit();
			isRegistered = true;
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			// return false;
		}
		return isRegistered;
	}

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		// TODO Auto-generated method stub
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select u from User u where u.uId=:uId and u.role=:role and u.password=:password";
			TypedQuery<UsersInfoBean> query = manager.createQuery(jpql, UsersInfoBean.class);
			query.setParameter("uId", id);
			query.setParameter("role", role);
			query.setParameter("password", password);
			UsersInfoBean rs = query.getSingleResult();
			if (rs != null) {
				UsersInfoBean record = manager.find(UsersInfoBean.class, id);
				record.setPassword(newPassword);
				transaction.commit();
				return true;
			} else {
				throw new LMSException("User doesnt exist");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public UsersInfoBean login(String email, String password) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select u from User u where u.email=:email and u.password=:password";
			TypedQuery<UsersInfoBean> query = manager.createQuery(jpql, UsersInfoBean.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			UsersInfoBean bean = query.getSingleResult();
			return bean;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfoBean> searchBookById(int bookId) {
		// TODO Auto-generated method stub
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from Book b where b.bookId=:bookId";
			TypedQuery<BookInfoBean> query = manager.createQuery(jpql, BookInfoBean.class);
			query.setParameter("bookId", bookId);
			List<BookInfoBean> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfoBean> searchBookByTitle(String bookName) {
		// TODO Auto-generated method stub
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from Book b where b.bookName=:bookName";
			TypedQuery<BookInfoBean> query = manager.createQuery(jpql, BookInfoBean.class);
			query.setParameter("bookName", bookName);
			List<BookInfoBean> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfoBean> searchBookByAuthor(String authorName) {
		// TODO Auto-generated method stub
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from Book b where b.authorName=:authorName";
			TypedQuery<BookInfoBean> query = manager.createQuery(jpql, BookInfoBean.class);
			query.setParameter("authorName", authorName);
			List<BookInfoBean> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfoBean> getBooksInfo() {
		// TODO Auto-generated method stub
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from Book b";
		TypedQuery<BookInfoBean> query = manager.createQuery(jpql, BookInfoBean.class);
		List<BookInfoBean> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}
}
