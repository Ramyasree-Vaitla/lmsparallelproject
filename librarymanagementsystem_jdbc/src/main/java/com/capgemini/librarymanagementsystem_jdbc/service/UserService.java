package com.capgemini.librarymanagementsystem_jdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.BoorowedBooksInfoBean;

public interface UserService {

	//BookInfoBean borrow(int bId);

	boolean request(int uId, int bId);

	List<BoorowedBooksInfoBean> borrowedBook(int uId);

	List<BookIssueInfoBean> bookHistoryDetails(int uId);

	boolean returnBook(int bId, int uId, String status);

}
