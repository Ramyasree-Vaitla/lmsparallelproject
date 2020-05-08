package com.capgemini.librarymanagementsystem.service;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfoBean;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem.factory.LibraryFactory;

public class UserServiceImplement implements UserService {

	private UserDAO dao = LibraryFactory.getUserDAO();

	@Override
	public boolean registerUser(UserInfoBean userInfoBean) {
		return dao.registerUser(userInfoBean);
	}

	@Override
	public UserInfoBean loginUser(String email, String password) {
		return dao.loginUser(email, password);
	}

	@Override
	public RequestInfoBean bookRequest(UserInfoBean userInfoBean, BookInfoBean bookInfoBean) {
		return dao.bookRequest(userInfoBean, bookInfoBean);
	}

	@Override
	public RequestInfoBean bookReturn(UserInfoBean student, BookInfoBean bookInfoBean) {
		return dao.bookReturn(student, bookInfoBean);
	}

	@Override
	public ArrayList<BookInfoBean> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public ArrayList<BookInfoBean> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public ArrayList<BookInfoBean> searchBookByCategory(String category) {
		return dao.searchBookByCategory(category);
	}

	@Override
	public ArrayList<BookInfoBean> getBooksInfo() {
		return dao.getBooksInfo();
	}

}