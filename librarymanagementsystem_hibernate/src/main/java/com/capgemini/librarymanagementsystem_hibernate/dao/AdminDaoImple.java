package com.capgemini.librarymanagementsystem_hibernate.dao;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.librarymanagementsystem_hibernate.dto.BookBorrowedInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookRequestInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.exception.LMSException;

public class AdminDaoImple implements AdminDao {

	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean addBook(BookInfoBean book) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(book);
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

	@SuppressWarnings("rawtypes")
	@Override
	public boolean bookIssue(int bId, int uId) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookBean b where b.bId=:bId";
			TypedQuery<BookInfoBean> query = manager.createQuery(jpql, BookInfoBean.class);
			query.setParameter("bId", bId);
			BookInfoBean rs = query.getSingleResult();
			if (rs != null) {
				String jpql1 = "select r from RequestDetailsBean r where r.uId=:uId and r.bId=:bId";
				TypedQuery<BookRequestInfoBean> query1 = manager.createQuery(jpql1, BookRequestInfoBean.class);
				query1.setParameter("uId", uId);
				query1.setParameter("bId", bId);
				List<BookRequestInfoBean> rs1 = query1.getResultList();
				if (!rs1.isEmpty() && rs1 != null) {
					transaction.begin();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					String issueDate = sdf.format(cal.getTime());
					cal.add(Calendar.DAY_OF_MONTH, 7);
					String returnDate = sdf.format(cal.getTime());
					BookIssueInfoBean issueBook = new BookIssueInfoBean();
					issueBook.setUId(uId);
					issueBook.setBId(bId);
					issueBook.setIssueDate(java.sql.Date.valueOf(issueDate));
					issueBook.setReturnDate(java.sql.Date.valueOf(returnDate));
					manager.persist(issueBook);
					transaction.commit();
					if (!rs1.isEmpty() && rs1 != null) {
						transaction.begin();
						Query bookName = manager.createQuery("select b.bookName from BookBean b where b.bId=:bId");
						bookName.setParameter("bId", bId);
						List book = bookName.getResultList();
						BookBorrowedInfoBean borrowedBooks = new BookBorrowedInfoBean();
						borrowedBooks.setUId(uId);
						borrowedBooks.setBId(bId);
						borrowedBooks.setBookName(book.get(0).toString());
						manager.persist(borrowedBooks);
						transaction.commit();
						return true;
					} else {
						throw new LMSException("Book Not issued");
					}
				} else {
					throw new LMSException("The respective user have not placed any request");
				}
			} else {
				throw new LMSException("There is no book exist with bookId" + bId);
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
	public boolean updateBook(BookInfoBean book) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookInfoBean record = manager.find(BookInfoBean.class, book.getBId());
			record.setBookName(book.getBookName());
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
	public boolean removeBook(int bId) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookInfoBean record = manager.find(BookInfoBean.class, bId);
			manager.remove(record);
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
	public List<UserInfoBean> showUsers() {
		// TODO Auto-generated method stub
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select u from UsersBean u";
		TypedQuery<UserInfoBean> query = manager.createQuery(jpql, UserInfoBean.class);
		List<UserInfoBean> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public List<BookRequestInfoBean> showRequests() {
		// TODO Auto-generated method stub
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select r from RequestDetailsBean r";
		TypedQuery<BookRequestInfoBean> query = manager.createQuery(jpql, BookRequestInfoBean.class);
		List<BookRequestInfoBean> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public List<BookIssueInfoBean> showIssuedBooks() {
		// TODO Auto-generated method stub
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookIssueBean b";
		TypedQuery<BookIssueInfoBean> query = manager.createQuery(jpql, BookIssueInfoBean.class);
		List<BookIssueInfoBean> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

}