package com.capgemini.librarymanagementsystem_jdbc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem_jdbc.dao.UserDao;
import com.capgemini.librarymanagementsystem_jdbc.dao.UserDaoImple;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.BoorowedBooksInfoBean;

public class UserDaoTest {

	private UserDao dao = new UserDaoImple();

	@Test
	public void testRequest() {
		boolean b = dao.request(909090, 975310);
		Assertions.assertTrue(b);
	}

	@Test
	public void testBookHistoryDetails() {
		List<BookIssueInfoBean> info = dao.bookHistoryDetails(4);
		Assertions.assertNotNull(info);
	}

	@Test
	public void testBookHistoryDetails1() {
		List<BookIssueInfoBean> info1 = dao.bookHistoryDetails(4);
		Assertions.assertNotNull(info1);
	}

	@Test
	public void testReturnBook() {
		boolean b = dao.returnBook(2, 909090, "returning");
		Assertions.assertTrue(b);
	}

	@Test
	public void testReturnBook1() {
		boolean b1 = dao.returnBook(2, 909090, "returning");
		Assertions.assertTrue(b1);
	}

	@Test
	public void testBorrowedBook() {
		List<BoorowedBooksInfoBean> b = dao.borrowedBook(2);
		Assertions.assertNotNull(b);
	}

	@Test
	public void testBorrowedBook1() {
		List<BoorowedBooksInfoBean> b1 = dao.borrowedBook(2);
		Assertions.assertNotNull(b1);
	}

}
