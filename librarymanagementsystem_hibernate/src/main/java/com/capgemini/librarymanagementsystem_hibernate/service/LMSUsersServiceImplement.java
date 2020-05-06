package com.capgemini.librarymanagementsystem_hibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_hibernate.dao.LMSUsersDAO;
import com.capgemini.librarymanagementsystem_hibernate.dto.LMSBookDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.LMSBookIssueDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.LMSBorrowedDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.LMSRequestDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.LMSUsersDetails;
import com.capgemini.librarymanagementsystem_hibernate.factory.LibraryFactory;

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
	public List<LMSBorrowedDetails> borrowedBook(int uId) {
		return dao.borrowedBook(uId);
	}

	@Override
	public List<LMSBookDetails> searchBookById(int bId) {
		return dao.searchBookById(bId);
	}

	@Override
	public List<LMSBookDetails> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public List<LMSBookDetails> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public List<LMSBookDetails> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public boolean returnBook(int bId, int uId, String status) {
		return dao.returnBook(bId, uId, status);
	}

	@Override
	public List<Integer> bookHistoryDetails(int uId) {
		return dao.bookHistoryDetails(uId);
	}

	@Override
	public List<LMSRequestDetails> showRequests() {
		return dao.showRequests();
	}

	@Override
	public List<LMSBookIssueDetails> showIssuedBooks() {
		return dao.showIssuedBooks();
	}

	@Override
	public List<LMSUsersDetails> showUsers() {
		return dao.showUsers();
	}

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		return dao.updatePassword(id, password, newPassword, role);
	}

}
