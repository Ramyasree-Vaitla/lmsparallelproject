package com.capgemini.librarymanagementsystem_hibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_hibernate.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.dto.BookRequestInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.dto.UserInfoBean;

public interface AdminService {

	boolean addBook(BookInfoBean book);

	boolean bookIssue(int bId, int uId);

	boolean updateBook(BookInfoBean book);

	boolean removeBook(int bId);

	List<UserInfoBean> showUsers();

	List<BookRequestInfoBean> showRequests();

	List<BookIssueInfoBean> showIssuedBooks();

}
