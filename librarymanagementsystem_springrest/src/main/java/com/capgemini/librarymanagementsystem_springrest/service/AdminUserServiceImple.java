package com.capgemini.librarymanagementsystem_springrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystem._springrest.dao.AdminUserDao;
import com.capgemini.librarymanagementsystem_springrest.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.UsersInfoBean;

@Service
public class AdminUserServiceImple implements AdminUserService {

	@Autowired
	private AdminUserDao dao;

	@Override
	public boolean register(UsersInfoBean user) {
		// TODO Auto-generated method stub
		return dao.register(user);
	}

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		// TODO Auto-generated method stub
		return dao.updatePassword(id, password, newPassword, role);
	}

	@Override
	public UsersInfoBean login(String email, String password) {
		// TODO Auto-generated method stub
		return dao.login(email, password);
	}

	@Override
	public List<BookInfoBean> getBooksInfo() {
		// TODO Auto-generated method stub
		return dao.getBooksInfo();
	}

	@Override
	public List<BookInfoBean> searchBookById(int bookId) {
		// TODO Auto-generated method stub
		return dao.searchBookById(bookId);
	}

	@Override
	public List<BookInfoBean> searchBookByTitle(String bookName) {
		// TODO Auto-generated method stub
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public List<BookInfoBean> searchBookByAuthor(String authorName) {
		// TODO Auto-generated method stub
		return dao.searchBookByAuthor(authorName);
	}

}
