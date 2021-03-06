package com.capgemini.librarymanagementsystem_jdbc.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dto.LMSBookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.LMSBookIssueDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.LMSBorrowedDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.LMSRequestDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.LMSUsersDetails;

public interface LMSUsersDAO {

	boolean register(LMSUsersDetails user);

	LMSUsersDetails login(String email, String password);

	boolean addBook(LMSBookDetails book);

	boolean removeBook(int bId);

	boolean updateBook(LMSBookDetails book);

	boolean issueBook(int bId, int uId);

	boolean request(int uId, int bId);

	List<LMSBorrowedDetails> borrowedBook(int uId);

	LinkedList<LMSBookDetails> searchBookById(int bId);

	LinkedList<LMSBookDetails> searchBookByTitle(String bookName);

	LinkedList<LMSBookDetails> searchBookByAuthor(String author);

	LinkedList<LMSBookDetails> getBooksInfo();

	boolean returnBook(int bId, int uId, String status);

	LinkedList<LMSBookIssueDetails> bookHistoryDetails(int uId);

	LinkedList<LMSRequestDetails> showRequests();

	LinkedList<LMSBookIssueDetails> showIssuedBooks();

	LinkedList<LMSUsersDetails> showUsers();

	boolean updatePassword(String email, String password, String newPassword, String role);
}
