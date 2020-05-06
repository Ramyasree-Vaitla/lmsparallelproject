package com.capgemini.librarymanagementsystem_hibernate.dao;

import java.util.List;

import com.capgemini.librarymanagementsystem_hibernate.dto.LMSBookDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.LMSBookIssueDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.LMSBorrowedDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.LMSRequestDetails;
import com.capgemini.librarymanagementsystem_hibernate.dto.LMSUsersDetails;

public interface LMSUsersDAO {

	boolean register(LMSUsersDetails user);

	LMSUsersDetails login(String email, String password);

	boolean addBook(LMSBookDetails book);

	boolean removeBook(int bId);

	boolean updateBook(LMSBookDetails book);

	boolean issueBook(int bId, int uId);

	boolean request(int uId, int bId);

	List<LMSBorrowedDetails> borrowedBook(int uId);

	List<LMSBookDetails> searchBookById(int bId);

	List<LMSBookDetails> searchBookByTitle(String bookName);

	List<LMSBookDetails> searchBookByAuthor(String author);

	List<LMSBookDetails> getBooksInfo();

	boolean returnBook(int bId, int uId, String status);

	List<Integer> bookHistoryDetails(int uId);

	List<LMSRequestDetails> showRequests();

	List<LMSBookIssueDetails> showIssuedBooks();

	List<LMSUsersDetails> showUsers();

	boolean updatePassword(int id, String password, String newPassword, String role);

}
