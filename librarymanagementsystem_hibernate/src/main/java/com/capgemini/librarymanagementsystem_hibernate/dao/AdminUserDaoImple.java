package com.capgemini.librarymanagementsystem_hibernate.dao;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capgemini.librarymanagementsystem_hibernate.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.exception.LMSException;

public class AdminUserDaoImple implements AdminUserDao {
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean register(com.capgemini.librarymanagementsystem_hibernate.dto.UserInfoBean user) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(user);
			transaction.commit();
			return true;
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
	public UserInfoBean login(String email, String password) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select u from UsersBean u where u.email=:email and u.password=:password";
			TypedQuery<UserInfoBean> query = manager.createQuery(jpql, UserInfoBean.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			UserInfoBean bean = query.getSingleResult();
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
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select u from UsersBean u where u.uId=:uId and u.role=:role and u.password=:password";
			TypedQuery<UserInfoBean> query = manager.createQuery(jpql, UserInfoBean.class);
			query.setParameter("uId", id);
			query.setParameter("role", role);
			query.setParameter("password", password);
			UserInfoBean rs = query.getSingleResult();
			if (rs != null) {
				UserInfoBean record = manager.find(UserInfoBean.class, id);
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
	public List<BookInfoBean> getBooksInfo() {
		// TODO Auto-generated method stub

		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookBean b";
		TypedQuery<BookInfoBean> query = manager.createQuery(jpql, BookInfoBean.class);
		List<BookInfoBean> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;

	}

	@Override
	public List<BookInfoBean> searchBookById(int bId) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BookBean b where b.bId=:bId";
			TypedQuery<BookInfoBean> query = manager.createQuery(jpql, BookInfoBean.class);
			query.setParameter("bId", bId);
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
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BookBean b where b.bookName=:bookName";
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
	public List<BookInfoBean> searchBookByAuthor(String author) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BookBean b where b.author=:author";
			TypedQuery<BookInfoBean> query = manager.createQuery(jpql, BookInfoBean.class);
			query.setParameter("author", author);
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

}