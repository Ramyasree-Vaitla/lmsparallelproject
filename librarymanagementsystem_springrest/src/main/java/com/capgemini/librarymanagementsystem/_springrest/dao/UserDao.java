package com.capgemini.librarymanagementsystem._springrest.dao;

import java.util.List;

import com.capgemini.librarymanagementsystem_springrest.dto.BookBorrowedInfoBean;

public interface UserDao {

	List<Integer> bookHistoryDetails(int uId);

	boolean request(int uId, int bId);

	List<BookBorrowedInfoBean> borrowedBook(int uId);

	boolean returnBook(int bId, int uId, String status);

}
