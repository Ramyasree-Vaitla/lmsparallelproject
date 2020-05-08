package com.capgemini.librarymanagementsystem.dao;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystem.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfoBean;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;

public interface UserDAO {

	boolean registerUser(UserInfoBean userInfoBean);

	UserInfoBean loginUser(String email, String password);

	ArrayList<BookInfoBean> getBooksInfo();

	ArrayList<BookInfoBean> searchBookByAuthor(String author);

	ArrayList<BookInfoBean> searchBookByTitle(String bookName);

	ArrayList<BookInfoBean> searchBookByCategory(String category);

	public RequestInfoBean bookRequest(UserInfoBean userInfoBean, BookInfoBean bookInfoBean);

	public RequestInfoBean bookReturn(UserInfoBean student, BookInfoBean bookInfoBean);

}
