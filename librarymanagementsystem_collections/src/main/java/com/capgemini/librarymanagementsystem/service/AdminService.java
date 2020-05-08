package com.capgemini.librarymanagementsystem.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.AdminInfoBean;
import com.capgemini.librarymanagementsystem.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfoBean;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;

public interface AdminService {

	boolean registerAdmin(AdminInfoBean adminInfoBean);

	AdminInfoBean loginAdmin(String email, String password);

	boolean addBook(BookInfoBean bookInfoBean);

	boolean removeBook(int id);

	boolean updateBook(BookInfoBean bookInfoBean);

	ArrayList<BookInfoBean> searchBookByTitle(String bookName);

	ArrayList<BookInfoBean> searchBookByAuthor(String author);

	ArrayList<BookInfoBean> searchBookByCategory(String category);

	ArrayList<BookInfoBean> getBooksInfo();

	List<UserInfoBean> showUsers();

	List<RequestInfoBean> showRequests();

	boolean bookIssue(UserInfoBean userInfoBean, BookInfoBean bookInfoBean);

	boolean isBookReceived(UserInfoBean userInfoBean, BookInfoBean bookInfoBean);

}