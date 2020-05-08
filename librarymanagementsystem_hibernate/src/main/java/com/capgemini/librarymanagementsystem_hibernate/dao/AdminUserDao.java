package com.capgemini.librarymanagementsystem_hibernate.dao;

import java.util.List;

import com.capgemini.librarymanagementsystem_hibernate.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_hibernate.dto.UserInfoBean;

public interface AdminUserDao {

	boolean register(UserInfoBean user);

	UserInfoBean login(String email, String password);

	boolean updatePassword(int id, String password, String newPassword, String role);

	List<BookInfoBean> getBooksInfo();

	List<BookInfoBean> searchBookById(int bId);

	List<BookInfoBean> searchBookByTitle(String bookName);

	List<BookInfoBean> searchBookByAuthor(String author);

}
