package com.capgemini.librarymanagementsystem_hibernate.dao;

import java.util.List;

import com.capgemini.librarymanagementsystem_hibernate.dto.BookBorrowedInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookIssueInfoBean;

public interface UserDao {

	// BookInfoBean borrow(int bId);

	boolean request(int uId, int bId);

	List<BookBorrowedInfoBean> borrowedBook(int uId);

	List<BookIssueInfoBean> bookHistoryDetails(int uId);

	boolean returnBook(int bId, int uId, String status);

}
