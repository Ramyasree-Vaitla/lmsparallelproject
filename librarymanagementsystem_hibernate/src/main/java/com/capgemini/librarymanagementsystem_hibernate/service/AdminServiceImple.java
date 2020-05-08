package com.capgemini.librarymanagementsystem_hibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_hibernate.dao.AdminDao;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookRequestInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.factory.LibraryFactory;

public class AdminServiceImple implements AdminService {

	private AdminDao dao = LibraryFactory.getAdminDao();

	@Override
	public boolean addBook(BookInfoBean book) {
		// TODO Auto-generated method stub
		return dao.addBook(book);
	}

	@Override
	public boolean bookIssue(int bId, int uId) {
		// TODO Auto-generated method stub
		return dao.bookIssue(bId, uId);
	}

	@Override
	public boolean updateBook(BookInfoBean book) {
		// TODO Auto-generated method stub
		return dao.updateBook(book);
	}

	@Override
	public boolean removeBook(int bId) {
		// TODO Auto-generated method stub
		return dao.removeBook(bId);
	}

	@Override
	public List<UserInfoBean> showUsers() {
		// TODO Auto-generated method stub
		return dao.showUsers();
	}

	@Override
	public List<BookRequestInfoBean> showRequests() {
		// TODO Auto-generated method stub
		return dao.showRequests();
	}

	@Override
	public List<BookIssueInfoBean> showIssuedBooks() {
		// TODO Auto-generated method stub
		return dao.showIssuedBooks();
	}

}
