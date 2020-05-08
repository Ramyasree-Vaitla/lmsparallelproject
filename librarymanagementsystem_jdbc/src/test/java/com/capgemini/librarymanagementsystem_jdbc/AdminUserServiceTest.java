package com.capgemini.librarymanagementsystem_jdbc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminUserService;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminUserServiceImple;

public class AdminUserServiceTest {

	private AdminUserService service = new AdminUserServiceImple();

	@Test
	public void testRegisterUser() {
		UserInfoBean user = new UserInfoBean();
		user.setFirstName("myz");
		user.setLastName("abc");
		user.setEmail("myz@gmail.com");
		user.setMobile(987654321);
		user.setPassword("myz@1212");
		user.setuId(13);
		user.setRole("student");
		boolean status = service.register(user);
		Assertions.assertTrue(status);

	}

	@Test
	public void testRegisterUser1() {
		UserInfoBean user = new UserInfoBean();
		user.setFirstName("myz");
		user.setLastName("myz");
		user.setEmail("myz@gmail.com");
		user.setMobile(987654321);
		user.setPassword("myz@1212");
		user.setuId(12);
		user.setRole("student");
		boolean status1 = service.register(user);
		Assertions.assertTrue(status1);

	}

	@Test
	public void testAuthUser() {
		UserInfoBean status = service.login("myz@gmail.com", "myz@1212");
		Assertions.assertNotNull(status);
	}

	@Test
	public void testAuthUser1() {
		UserInfoBean status1 = service.login("myz@gmail.com", "myz@1212");
		Assertions.assertNotNull(status1);
	}

	@Test
	public void testGetBooksInfo() {
		List<BookInfoBean> b = service.getBooksInfo();
		Assertions.assertNotNull(b);
	}

	@Test
	public void testGetBooksInfo1() {
		List<BookInfoBean> b1 = service.getBooksInfo();
		Assertions.assertNotNull(b1);
	}

	@Test
	public void testUpdatePassword() {
		boolean msg = service.updatePassword("sri@gmail.com", "sri@123", "sri@1234", "student");
		Assertions.assertTrue(msg);

	}

	@Test
	public void testUpdatePassword1() {
		boolean msg1 = service.updatePassword("sri@gmail.com", "sri@123", "sri@1234", "student");
		Assertions.assertTrue(msg1);

	}

	@Test
	public void testSearchBookById() {
		List<BookInfoBean> b = service.searchBookById(121212);
		Assertions.assertNotNull(b);
	}

	@Test
	public void testSearchBookById1() {
		List<BookInfoBean> b1 = service.searchBookById(121212);
		Assertions.assertNotNull(b1);
	}

	@Test
	public void testSearchBookByAuthor() {
		List<BookInfoBean> check = service.searchBookByAuthor("williams");
		Assertions.assertNotNull(check);

	}

	@Test
	public void testSearchBookByAuthor1() {
		List<BookInfoBean> check1 = service.searchBookByAuthor("williams");
		Assertions.assertNotNull(check1);

	}

	@Test
	public void testSearchBookByTitle() {
		List<BookInfoBean> info = service.searchBookByTitle("Java");
		Assertions.assertNotNull(info);

	}

	@Test
	public void testSearchBookByTitle1() {
		List<BookInfoBean> info1 = service.searchBookByTitle("Java");
		Assertions.assertNotNull(info1);

	}

}