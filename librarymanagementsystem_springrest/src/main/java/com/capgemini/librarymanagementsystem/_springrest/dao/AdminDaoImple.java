package com.capgemini.librarymanagementsystem._springrest.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagementsystem_springrest.dto.BookBorrowedInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.BookRequestInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.UsersInfoBean;
import com.capgemini.librarymanagementsystem_springrest.exceptions.LMSException;

@Repository
public class AdminDaoImple implements AdminDao {

	@PersistenceUnit
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean addBook(BookInfoBean book) {
		// TODO Auto-generated method stub
		try {
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

	@Override
	public boolean removeBook(int bookId) {
		// TODO Auto-generated method stub
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookInfoBean record = manager.find(BookInfoBean.class, bookId);
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
	public boolean updateBook(BookInfoBean book) {
		// TODO Auto-generated method stub

		try {
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

	@SuppressWarnings("rawtypes")
	@Override
	public boolean issueBook(int bookId, int uId) {
		// TODO Auto-generated method stub
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from Book b where b.bookId=:bookId";
			TypedQuery<BookInfoBean> query = manager.createQuery(jpql, BookInfoBean.class);
			query.setParameter("bookId", bookId);
			BookInfoBean rs = query.getSingleResult();
			if (rs != null) {
				String jpql1 = "select r from RequestDetails r where r.uId=:uId and r.bId=:bId";
				TypedQuery<BookRequestInfoBean> query1 = manager.createQuery(jpql1, BookRequestInfoBean.class);
				//
				query1.setParameter("uId", uId);
				query1.setParameter("bookId", bookId);
				List<BookRequestInfoBean> rs1 = query1.getResultList();
				if (!rs1.isEmpty() && rs1 != null) {
					transaction.begin();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					String issueDate = sdf.format(cal.getTime());
					cal.add(Calendar.DAY_OF_MONTH, 7);
					String returnDate = sdf.format(cal.getTime());
					BookIssueInfoBean issueBook = new BookIssueInfoBean();
					issueBook.setuId(uId);
					issueBook.setbId(bookId);
					issueBook.setIssueDate(java.sql.Date.valueOf(issueDate));
					issueBook.setReturnDate(java.sql.Date.valueOf(returnDate));
					manager.persist(issueBook);
					transaction.commit();
					if (!rs1.isEmpty() && rs1 != null) {
						transaction.begin();
						Query bookName = manager.createQuery("select b.bookName from Book b where b.bookId=:bookId");
						bookName.setParameter("bookId", bookId);
						List book = bookName.getResultList();
						BookBorrowedInfoBean borrowedBooks = new BookBorrowedInfoBean();
						//
						borrowedBooks.setuId(uId);
						borrowedBooks.setbId(bookId);
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
				throw new LMSException("There is no book exist with bookId" + bookId);
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
	public List<BookRequestInfoBean> showRequests() {
		// TODO Auto-generated method stub
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select r from RequestDetails r";
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
		String jpql = "select b from BookIssueDetails b";
		TypedQuery<BookIssueInfoBean> query = manager.createQuery(jpql, BookIssueInfoBean.class);
		List<BookIssueInfoBean> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;

	}

	@Override
	public List<UsersInfoBean> showUsers() {
		// TODO Auto-generated method stub

		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select u from User u";
		TypedQuery<UsersInfoBean> query = manager.createQuery(jpql, UsersInfoBean.class);
		List<UsersInfoBean> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}
}
