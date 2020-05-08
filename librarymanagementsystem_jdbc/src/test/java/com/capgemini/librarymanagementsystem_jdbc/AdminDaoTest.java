package com.capgemini.librarymanagementsystem_jdbc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem_jdbc.dao.AdminDao;
import com.capgemini.librarymanagementsystem_jdbc.dao.AdminDaoImple;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.RequestBookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.UserInfoBean;

public class AdminDaoTest {
	
	private AdminDao dao = new AdminDaoImple();

	@Test
	public void testAddBook() {
		BookInfoBean book = new BookInfoBean();
		book.setBId(1);
		book.setBookName("JDBC");
		book.setAuthor("James");
		book.setCategory("Java");
		book.setPublisher("Sia");
		boolean status = dao.addBook(book);
		Assertions.assertTrue(status);
	}

	@Test
	public void testAddBooks() {
		BookInfoBean books = new BookInfoBean();
		books.setBId(1);
		books.setBookName("Hibernate");
		books.setAuthor("Rudyard");
		books.setCategory("Java");
		books.setPublisher("james");
		boolean check = dao.addBook(books);
		Assertions.assertTrue(check);
	}

	@Test
	public void testUpdateBook() {
		BookInfoBean book1 = new BookInfoBean();
		book1.setBId(1);
		book1.setBookName("JDBC");
		book1.setAuthor("williams");
		book1.setCategory("java");
		book1.setPublisher("Sia");
		boolean msg = dao.updateBook(book1);
		Assertions.assertTrue(msg);

	}

	@Test
	public void testUpdateBooks() {
		BookInfoBean book = new BookInfoBean();
		book.setBId(1);
		book.setBookName("JDBC");
		book.setAuthor("williams");
		book.setCategory("java");
		book.setPublisher("Sia");
		boolean check = dao.updateBook(book);
		Assertions.assertTrue(check);
	}

	@Test
	public void testRemoveBook() {
		BookInfoBean b = new BookInfoBean();
		b.setBId(1);
		b.setBookName("JDBC");
		b.setAuthor("James");
		b.setCategory("Java");
		b.setPublisher("Sia");
		boolean status = dao.removeBook(1);
		Assertions.assertTrue(status);

	}

	@Test
	public void testRemoveBook1() {
		BookInfoBean b1 = new BookInfoBean();
		b1.setBId(1);
		b1.setBookName("JDBC");
		b1.setAuthor("James");
		b1.setCategory("Java");
		b1.setPublisher("Sia");
		boolean check = dao.removeBook(1);
		Assertions.assertTrue(check);

	}

	@Test
	public void testBookIssue() {
		BookIssueInfoBean bookDetails = new BookIssueInfoBean();
		bookDetails.setbId(151617);
		bookDetails.setuId(102030);
		bookDetails.setIssueDate(null);
		bookDetails.setReturnDate(null);
		boolean msg = dao.bookIssue(151617, 102030);
		Assertions.assertTrue(msg);
	}

	@Test
	public void testBookIssue1() {
		BookIssueInfoBean bookDetails = new BookIssueInfoBean();
		bookDetails.setbId(171819);
		bookDetails.setuId(304050);
		bookDetails.setIssueDate(null);
		bookDetails.setReturnDate(null);
		boolean msg = dao.bookIssue(171819, 203040);
		Assertions.assertTrue(msg);
	}

	@Test
	public void testShowRequests() {
		List<RequestBookInfoBean> msg = dao.showRequests();
		Assertions.assertNotNull(msg);

	}

	@Test
	public void testShowRequests1() {
		List<RequestBookInfoBean> msg1 = dao.showRequests();
		Assertions.assertNotNull(msg1);

	}

	@Test
	public void testShowIssuedBooks() {
		List<BookIssueInfoBean> book = dao.showIssuedBooks();
		Assertions.assertNotNull(book);
	}

	@Test
	public void testShowIssuedBooks1() {
		List<BookIssueInfoBean> book1 = dao.showIssuedBooks();
		Assertions.assertNotNull(book1);
	}

	@Test
	public void testShowUsers() {
		List<UserInfoBean> user = dao.showUsers();
		Assertions.assertNotNull(user);
	}

	@Test
	public void testShowUsers1() {
		List<UserInfoBean> user1 = dao.showUsers();
		Assertions.assertNotNull(user1);
	}


}
