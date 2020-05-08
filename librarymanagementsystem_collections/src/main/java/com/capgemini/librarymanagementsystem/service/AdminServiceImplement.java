package com.capgemini.librarymanagementsystem.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dao.AdminDAO;
import com.capgemini.librarymanagementsystem.dto.AdminInfoBean;
import com.capgemini.librarymanagementsystem.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfoBean;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem.factory.LibraryFactory;

public class AdminServiceImplement implements AdminService {

	private AdminDAO dao = LibraryFactory.getAdminDAO();

	@Override
	public boolean registerAdmin(AdminInfoBean adminInfoBean) {
		return dao.registerAdmin(adminInfoBean);
	}

	@Override
	public AdminInfoBean loginAdmin(String email, String password) {
		return dao.loginAdmin(email, password);
	}

	@Override
	public boolean addBook(BookInfoBean bookInfoBean) {
		return dao.addBook(bookInfoBean);
	}

	@Override
	public boolean removeBook(int id) {
		return dao.removeBook(id);
	}

	@Override
	public boolean updateBook(BookInfoBean bookInfoBean) {
		return dao.updateBook(bookInfoBean);
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

	@Override
	public List<UserInfoBean> showUsers() {
		return dao.showUsers();
	}

	@Override
	public List<RequestInfoBean> showRequests() {
		return dao.showRequests();
	}

	@Override
	public boolean bookIssue(UserInfoBean userInfoBean, BookInfoBean bookInfoBean) {
		return dao.bookIssue(userInfoBean, bookInfoBean);
	}

	@Override
	public boolean isBookReceived(UserInfoBean userInfoBean, BookInfoBean bookInfoBean) {
		return dao.isBookReceived(userInfoBean, bookInfoBean);
	}

}
