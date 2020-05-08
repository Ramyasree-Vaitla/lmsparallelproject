package com.capgemini.librarymanagementsystem_jdbc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.BoorowedBooksInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.RequestBookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminService;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminServiceImple;
import com.capgemini.librarymanagementsystem_jdbc.service.UserService;
import com.capgemini.librarymanagementsystem_jdbc.service.UserServiceImple;

public class UserServiceTest {

 private UserService service = new UserServiceImple();
	
	@Test
	public void testRequest() {
		boolean b = service.request(909090, 975310);
		Assertions.assertTrue(b);
	}
	@Test
	public void testBookHistoryDetails() {
		List<BookIssueInfoBean> info = service.bookHistoryDetails(4);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testBookHistoryDetails1() {
		List<BookIssueInfoBean> info1 = service.bookHistoryDetails(4);
		Assertions.assertNotNull(info1);
	}
	@Test
	public void testReturnBook() {
		boolean b=service.returnBook(2,909090,"returning");
		Assertions.assertTrue(b);
	}
	@Test
	public void testReturnBook1() {
		boolean b1=service.returnBook(2,909090,"returning");
		Assertions.assertTrue(b1);
	}
	@Test
	public void testBorrowedBook() {
		List<BoorowedBooksInfoBean> b=service.borrowedBook(2);
		Assertions.assertNotNull(b);
	}
	@Test
	public void testBorrowedBook1() {
		List<BoorowedBooksInfoBean> b1=service.borrowedBook(2);
		Assertions.assertNotNull(b1);
	}
	
	


}