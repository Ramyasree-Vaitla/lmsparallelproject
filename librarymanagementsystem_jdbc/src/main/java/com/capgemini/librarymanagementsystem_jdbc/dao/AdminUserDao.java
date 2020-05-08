package com.capgemini.librarymanagementsystem_jdbc.dao;

import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.UserInfoBean;

public interface AdminUserDao {

	boolean register(UserInfoBean user);

	UserInfoBean login(String email, String password);

	boolean updatePassword(String email, String password, String newPassword, String role);

	List<BookInfoBean> getBooksInfo();

	List<BookInfoBean> searchBookById(int bId);

	List<BookInfoBean> searchBookByTitle(String bookName);

	List<BookInfoBean> searchBookByAuthor(String author);

}
