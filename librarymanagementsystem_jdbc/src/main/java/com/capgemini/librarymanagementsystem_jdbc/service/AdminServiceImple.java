package com.capgemini.librarymanagementsystem_jdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dao.AdminDao;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.RequestBookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.factory.LibraryFactory;

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
	public List<RequestBookInfoBean> showRequests() {
		// TODO Auto-generated method stub
		return dao.showRequests();
		
	}

	@Override
	public List<BookIssueInfoBean> showIssuedBooks() {
		// TODO Auto-generated method stub
		return dao.showIssuedBooks();
	}

}
