package com.capgemini.librarymanagementsystem_jdbc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem_jdbc.dao.AdminUserDao;
import com.capgemini.librarymanagementsystem_jdbc.dao.AdminUserDaoImple;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.UserInfoBean;

public class AdminUserDaoTest {
	private AdminUserDao dao = new AdminUserDaoImple();

	@Test
	public void testRegisterUser() {
		UserInfoBean user = new UserInfoBean();
		user.setFirstName("xyz");
		user.setLastName("abc");
		user.setEmail("xyz@gmail.com");
		user.setMobile(987654321);
		user.setPassword("xyz@1212");
		user.setuId(12);
		user.setRole("student");
		boolean status = dao.register(user);
		Assertions.assertTrue(status);

	}

	@Test
	public void testRegisterUser1() {
		UserInfoBean user = new UserInfoBean();
		user.setFirstName("xyz");
		user.setLastName("abc");
		user.setEmail("xyz@gmail.com");
		user.setMobile(987654321);
		user.setPassword("xyz@1212");
		user.setuId(12);
		user.setRole("student");
		boolean status1 = dao.register(user);
		Assertions.assertTrue(status1);

	}

	@Test
	public void testAuthUser() {
		UserInfoBean status = dao.login("xyz@gmail.com", "xyz@1212");
		Assertions.assertNotNull(status);
	}

	@Test
	public void testAuthUser1() {
		UserInfoBean status1 = dao.login("xyz@gmail.com", "xyz@1212");
		Assertions.assertNotNull(status1);
	}

	@Test
	public void testGetBooksInfo() {
		List<BookInfoBean> b = dao.getBooksInfo();
		Assertions.assertNotNull(b);
	}

	@Test
	public void testGetBooksInfo1() {
		List<BookInfoBean> b1 = dao.getBooksInfo();
		Assertions.assertNotNull(b1);
	}

	@Test
	public void testUpdatePassword() {
		boolean msg = dao.updatePassword("sri@gmail.com", "sri@123", "sri@1234", "student");
		Assertions.assertTrue(msg);

	}

	@Test
	public void testUpdatePassword1() {
		boolean msg1 = dao.updatePassword("sri@gmail.com", "sri@123", "sri@1234", "student");
		Assertions.assertTrue(msg1);

	}

	@Test
	public void testSearchBookById() {
		List<BookInfoBean> b = dao.searchBookById(121212);
		Assertions.assertNotNull(b);
	}

	@Test
	public void testSearchBookById1() {
		List<BookInfoBean> b1 = dao.searchBookById(121212);
		Assertions.assertNotNull(b1);
	}

	@Test
	public void testSearchBookByAuthor() {
		List<BookInfoBean> check = dao.searchBookByAuthor("williams");
		Assertions.assertNotNull(check);

	}

	@Test
	public void testSearchBookByAuthor1() {
		List<BookInfoBean> check1 = dao.searchBookByAuthor("williams");
		Assertions.assertNotNull(check1);

	}

	@Test
	public void testSearchBookByTitle() {
		List<BookInfoBean> info = dao.searchBookByTitle("Java");
		Assertions.assertNotNull(info);

	}

	@Test
	public void testSearchBookByTitle1() {
		List<BookInfoBean> info1 = dao.searchBookByTitle("Java");
		Assertions.assertNotNull(info1);

	}

}
