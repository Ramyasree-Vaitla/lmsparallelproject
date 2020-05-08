package com.capgemini.librarymanagementsystem_jdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dao.UserDao;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.BoorowedBooksInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.factory.LibraryFactory;

public class UserServiceImple implements UserService {

	private UserDao dao = LibraryFactory.getUserDao();

	/*
	 * @Override public BookInfoBean borrow(int bId) { // TODO Auto-generated method
	 * stub return null; }
	 */

	@Override
	public boolean request(int uId, int bId) {
		// TODO Auto-generated method stub
		return dao.request(uId, bId);
	}

	@Override
	public List<BoorowedBooksInfoBean> borrowedBook(int uId) {
		// TODO Auto-generated method stub
		return dao.borrowedBook(uId);
	}

	@Override
	public List<BookIssueInfoBean> bookHistoryDetails(int uId) {
		// TODO Auto-generated method stub
		return dao.bookHistoryDetails(uId);
	}

	@Override
	public boolean returnBook(int bId, int uId, String status) {
		// TODO Auto-generated method stub
		return dao.returnBook(bId, uId, status);
	}

}
