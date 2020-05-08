package com.capgemini.librarymanagementsystem._springrest.dao;

import java.util.List;

import com.capgemini.librarymanagementsystem_springrest.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.BookRequestInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.UsersInfoBean;

public interface AdminDao {

	boolean addBook(BookInfoBean book);

	boolean removeBook(int bookId);

	boolean updateBook(BookInfoBean book);

	boolean issueBook(int bookId, int uId);

	List<BookRequestInfoBean> showRequests();

	List<BookIssueInfoBean> showIssuedBooks();

	List<UsersInfoBean> showUsers();

}
