package com.capgemini.librarymanagementsystem_springrest.service;

import java.util.List;

import com.capgemini.librarymanagementsystem_springrest.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.UsersInfoBean;

public interface AdminUserService {

	boolean register(UsersInfoBean user);

	boolean updatePassword(int id, String password, String newPassword, String role);

	UsersInfoBean login(String email, String password);

	List<BookInfoBean> getBooksInfo();

	List<BookInfoBean> searchBookById(int bookId);

	List<BookInfoBean> searchBookByTitle(String bookName);

	List<BookInfoBean> searchBookByAuthor(String authorName);
}
