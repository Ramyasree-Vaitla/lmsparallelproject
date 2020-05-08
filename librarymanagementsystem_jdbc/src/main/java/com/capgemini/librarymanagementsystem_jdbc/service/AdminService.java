package com.capgemini.librarymanagementsystem_jdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.RequestBookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.UserInfoBean;

public interface AdminService {

	boolean addBook(BookInfoBean book);

	boolean bookIssue(int bId, int uId);

	boolean updateBook(BookInfoBean book);

	boolean removeBook(int bId);

	List<UserInfoBean> showUsers();

	List<RequestBookInfoBean> showRequests();

	List<BookIssueInfoBean> showIssuedBooks();

}
