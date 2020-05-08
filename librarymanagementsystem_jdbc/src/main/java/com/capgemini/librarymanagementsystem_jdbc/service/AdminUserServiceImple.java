package com.capgemini.librarymanagementsystem_jdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dao.AdminUserDao;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.factory.LibraryFactory;

public class AdminUserServiceImple implements AdminUserService {

	private AdminUserDao dao = LibraryFactory.getAdminUserDao();

	@Override
	public boolean register(UserInfoBean user) {
		// TODO Auto-generated method stub
		return dao.register(user);
	}

	@Override
	public UserInfoBean login(String email, String password) {
		// TODO Auto-generated method stub
		return dao.login(email, password);
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		// TODO Auto-generated method stub
		return dao.updatePassword(email, password, newPassword, role);
	}

	@Override
	public List<BookInfoBean> getBooksInfo() {
		// TODO Auto-generated method stub
		return dao.getBooksInfo();
	}

	@Override
	public List<BookInfoBean> searchBookById(int bId) {
		// TODO Auto-generated method stub
		return dao.searchBookById(bId);
	}

	@Override
	public List<BookInfoBean> searchBookByTitle(String bookName) {
		// TODO Auto-generated method stub
		return dao.searchBookByTitle(bookName);

	}

	@Override
	public List<BookInfoBean> searchBookByAuthor(String author) {
		// TODO Auto-generated method stub

		return dao.searchBookByAuthor(author);
	}

}
