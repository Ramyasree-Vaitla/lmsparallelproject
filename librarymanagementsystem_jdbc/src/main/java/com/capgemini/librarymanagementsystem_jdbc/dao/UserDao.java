package com.capgemini.librarymanagementsystem_jdbc.dao;

import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.BoorowedBooksInfoBean;

public interface UserDao {

	//BookInfoBean borrow(int bId);

	boolean request(int uId, int bId);

	List<BoorowedBooksInfoBean> borrowedBook(int uId);

	List<BookIssueInfoBean> bookHistoryDetails(int uId);

	boolean returnBook(int bId, int uId, String status);

}
