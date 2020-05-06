package com.capgemini.librarymanagementsystem_jdbc.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dao.LMSUsersDAO;
import com.capgemini.librarymanagementsystem_jdbc.dto.LMSBookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.LMSBookIssueDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.LMSBorrowedDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.LMSRequestDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.LMSUsersDetails;
import com.capgemini.librarymanagementsystem_jdbc.factory.LibraryFactory;

public class LMSUsersServiceImplement implements LMSUsersService {

	private LMSUsersDAO dao = LibraryFactory.getUsersDao();

	@Override
	public boolean register(LMSUsersDetails user) {
		return dao.register(user);
	}

	@Override
	public LMSUsersDetails login(String email, String password) {
		return dao.login(email, password);
	}

	@Override
	public boolean addBook(LMSBookDetails book) {
		return dao.addBook(book);
	}

	@Override
	public boolean removeBook(int bId) {
		return dao.removeBook(bId);
	}

	@Override
	public boolean updateBook(LMSBookDetails book) {
		return dao.updateBook(book);
	}

	@Override
	public boolean issueBook(int bId, int uId) {
		return dao.issueBook(bId, uId);
	}

	@Override
	public boolean request(int uId, int bId) {
		return dao.request(uId, bId);
	}

	@Override
	public LinkedList<LMSBookDetails> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public LinkedList<LMSBookDetails> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public LinkedList<LMSBookDetails> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public boolean returnBook(int bId, int uId, String status) {
		return dao.returnBook(bId, uId, status);
	}

	@Override
	public LinkedList<LMSBookIssueDetails> bookHistoryDetails(int uId) {
		return dao.bookHistoryDetails(uId);
	}

	@Override
	public List<LMSBorrowedDetails> borrowedBook(int uId) {
		return dao.borrowedBook(uId);
	}

	@Override
	public LinkedList<LMSBookDetails> searchBookById(int bId) {
		return dao.searchBookById(bId);
	}

	@Override
	public LinkedList<LMSRequestDetails> showRequests() {
		return dao.showRequests();
	}

	@Override
	public LinkedList<LMSBookIssueDetails> showIssuedBooks() {
		return dao.showIssuedBooks();
	}

	@Override
	public LinkedList<LMSUsersDetails> showUsers() {
		return dao.showUsers();
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		return dao.updatePassword(email, password, newPassword, role);
	}

}
