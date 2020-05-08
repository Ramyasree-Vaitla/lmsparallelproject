package com.capgemini.librarymanagementsystem_hibernate.dao;

import java.io.FileInputStream;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
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
import com.capgemini.librarymanagementsystem_hibernate.exception.LMSException;

public class UserDaoImple implements UserDao {
	
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;


	@Override
	public boolean request(int uId, int bId) {
		// TODO Auto-generated method stub
		int count=0;
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookBean b where b.bId=:bId";
			TypedQuery<BookInfoBean> query = manager.createQuery(jpql,BookInfoBean.class);
			query.setParameter("bId", bId);
			List rs = query.getResultList();
			if(rs != null) {
				String jpql1 = "select b from BorrowedBooksBean b where b.uId=:uId and b.bId=:bId";
				TypedQuery<BookBorrowedInfoBean> query1 = (TypedQuery<BookBorrowedInfoBean>) manager.createQuery(jpql1,BookBorrowedInfoBean.class);
				query1.setParameter("uId", uId);
				query1.setParameter("bId", bId);
				List rs1 = query1.getResultList();
				if( rs1.isEmpty() || rs1==null ) {
					String jpql2 = "select b from BookIssueBean b where b.uId=:uId";
					TypedQuery<BookIssueInfoBean> query2 = (TypedQuery<BookIssueInfoBean>) manager.createQuery(jpql2,BookIssueInfoBean.class);
					query2.setParameter("uId", uId);
					List<BookIssueInfoBean> rs2 = query2.getResultList();
					for(BookIssueInfoBean p : rs2) {
						noOfBooks = count++;
					}
					if(noOfBooks<3) {
						Query bookName = manager.createQuery("select b.bookName from BookBean b where b.bId=:bookId");
						bookName.setParameter("bookId", bId);
						List book = bookName.getResultList();
						Query email = manager.createQuery("select u.email from UsersBean u where u.uId=:user_Id");
						email.setParameter("user_Id", uId);
						List userEmail = email.getResultList();
						transaction.begin();
						BookRequestInfoBean request = new BookRequestInfoBean();
						request.setUId(uId);
						request.setBId(bId);
						request.setEmail(userEmail.get(0).toString());
						request.setBookName(book.get(0).toString());
						manager.persist(request);
						transaction.commit();
						return true;

					}else {
						throw new LMSException("You have crossed the book limit");
					}
				}else {
					throw new LMSException("You have already borrowed the requested book");
				}
			}else {
				throw new LMSException("The book with requested id is not present");
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
	public List<BookBorrowedInfoBean> borrowedBook(int uId) {
		// TODO Auto-generated method stub
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BorrowedBooksBean b where b.uId=:uId";
			TypedQuery<BookBorrowedInfoBean> query = manager.createQuery(jpql,BookBorrowedInfoBean.class);
			query.setParameter("uId", uId);
			List<BookBorrowedInfoBean> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			manager.close();
			factory.close();
		}
	}
	

	@Override
	public List<BookIssueInfoBean> bookHistoryDetails(int uId) {
		// TODO Auto-generated method stub
		int count=0;
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookIssueBean b";
		TypedQuery<BookIssueInfoBean> query = manager.createQuery(jpql,BookIssueInfoBean.class);
		List<BookIssueInfoBean> recordList = query.getResultList();
		for(BookIssueInfoBean p : recordList) {
			noOfBooks = count++;
		}
		List<Integer> list = new LinkedList<Integer>();
		list.add(noOfBooks);
		manager.close();
		factory.close();
		return list;
}
	@Override
	public boolean returnBook(int bId, int uId, String status) {
		// TODO Auto-generated method stub
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookBean b where b.bId=:bId";
			TypedQuery<BookInfoBean> query = manager.createQuery(jpql,BookInfoBean.class);
			query.setParameter("bId", bId);
			BookInfoBean rs = query.getSingleResult();
			if(rs != null) {
				String jpql1 = "select b from BookIssueBean b where b.bId=:bId and b.uId=:uId ";
				TypedQuery<BookIssueInfoBean> query1 = manager.createQuery(jpql1,BookIssueInfoBean.class);
				query1.setParameter("bId", bId);
				query1.setParameter("uId", uId);
				BookIssueInfoBean rs1 = query1.getSingleResult();
				if(rs1 != null) {
					//Date issueDate = rs1.getIssueDate();
					Calendar cal = Calendar.getInstance();
					java.util.Date returnDate = cal.getTime();
					long difference = issueDate.getTime() - returnDate.getTime();
					float daysBetween = (difference / (1000*60*60*24));
					if(daysBetween>7.0) {
						transaction.begin();
						float fine = daysBetween*5;
						System.out.println("The user has to pay the fine of the respective book of Rs:"+fine);
						if(status=="yes") {
							transaction.begin();
							/*
								String jpql2 = "select b from BookIssueBean b where b.bId=:bId and b.uId=:uId";
								Query query2 = manager.createQuery(jpql2);
								query2.setParameter("bId", bId);
								query2.setParameter("uId", uId);
								BookIssueBean bib = (BookIssueBean) query2.getSingleResult();
							 */
							//int bib_Id = rs1.getId();
							manager.remove(rs1);
							transaction.commit();


							transaction.begin();
							String jpql3 = "select b from BorrowedBooksBean b  where b.bId=:bId and b.uId=:uId";
							Query query3 = manager.createQuery(jpql3);
							query3.setParameter("bId", bId);
							query3.setParameter("uId", uId);
							BookBorrowedInfoBean bbb = (BookBorrowedInfoBean) query3.getSingleResult();
							//int bbb_Id = bbb.getId();
							manager.remove(bbb);
							transaction.commit();

							transaction.begin();
							String jpql4 = "select r from RequestDetailsBean r where r.bId=:bId and r.uId=:uId";
							Query query4 = manager.createQuery(jpql4);
							query4.setParameter("bId", bId);
							query4.setParameter("uId", uId);
							BookRequestInfoBean rdb = (BookRequestInfoBean) query4.getSingleResult();
							//int rdb_Id = rdb.getId();
							manager.remove(rdb);
							transaction.commit();
							return true;
						}else {
							throw new LMSException("The User has to pay fine for delaying book return");
						}
					}else {
						transaction.begin();
						/*
							String jpql2 = "select b from BookIssueBean b where b.bId=:bId and b.uId=:uId";
							Query query2 = manager.createQuery(jpql2);
							query2.setParameter("bId", bId);
							query2.setParameter("uId", uId);
							BookIssueBean bib = (BookIssueBean) query2.getSingleResult();
						 */
						//int bib_Id = rs1.getId();
						manager.remove(rs1);
						transaction.commit();


						transaction.begin();
						String jpql3 = "select b from BorrowedBooksBean b  where b.bId=:bId and b.uId=:uId";
						Query query3 = manager.createQuery(jpql3);
						query3.setParameter("bId", bId);
						query3.setParameter("uId", uId);
						BookBorrowedInfoBean bbb = (BookBorrowedInfoBean) query3.getSingleResult();
						//int bbb_Id = bbb.getId();
						manager.remove(bbb);
						transaction.commit();

						transaction.begin();
						String jpql4 = "select r from RequestDetailsBean r where r.bId=:bId and r.uId=:uId";
						Query query4 = manager.createQuery(jpql4);
						query4.setParameter("bId", bId);
						query4.setParameter("uId", uId);
						BookRequestInfoBean rdb = (BookRequestInfoBean) query4.getSingleResult();
					
						manager.remove(rdb);
						transaction.commit();
						return true;
					}

				}else {
					throw new LMSException("This respective user hasn't borrowed any book");
				}
			}else {
				throw new LMSException("book doesnt exist");
			}

		}catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		}finally {
			manager.close();
			factory.close();
		}
	}

}
