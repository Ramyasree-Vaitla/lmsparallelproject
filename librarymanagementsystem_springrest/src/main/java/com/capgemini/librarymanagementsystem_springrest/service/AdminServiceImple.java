package com.capgemini.librarymanagementsystem_springrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystem._springrest.dao.AdminDao;
import com.capgemini.librarymanagementsystem_springrest.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.BookRequestInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.UsersInfoBean;

@Service
public class AdminServiceImple implements AdminService {

	@Autowired
	private AdminDao dao;

	@Override
	public boolean addBook(BookInfoBean book) {
		// TODO Auto-generated method stub

		return dao.addBook(book);
	}

	@Override
	public boolean removeBook(int bookId) {
		// TODO Auto-generated method stub
		return dao.removeBook(bookId);
	}

	@Override
	public boolean updateBook(BookInfoBean book) {
		// TODO Auto-generated method stub
		return dao.updateBook(book);
	}

	@Override
	public boolean issueBook(int bookId, int uId) {
		// TODO Auto-generated method stub
		return dao.issueBook(bookId, uId);
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

	@Override
	public List<UsersInfoBean> showUsers() {
		// TODO Auto-generated method stub
		return dao.showUsers();
	}

}
