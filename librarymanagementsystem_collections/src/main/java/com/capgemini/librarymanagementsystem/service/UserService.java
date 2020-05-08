package com.capgemini.librarymanagementsystem.service;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystem.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfoBean;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;

public interface UserService {

	boolean registerUser(UserInfoBean userInfoBean);

	UserInfoBean loginUser(String email, String password);

	public RequestInfoBean bookRequest(UserInfoBean userInfoBean, BookInfoBean bookInfoBean);

	public RequestInfoBean bookReturn(UserInfoBean student, BookInfoBean bookInfoBean);

	// Book borrowBook(int id);
	ArrayList<BookInfoBean> searchBookByTitle(String bookName);

	ArrayList<BookInfoBean> searchBookByAuthor(String author);

	ArrayList<BookInfoBean> searchBookByCategory(String category);

	ArrayList<BookInfoBean> getBooksInfo();

}