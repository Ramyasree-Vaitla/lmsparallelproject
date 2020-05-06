package com.capgemini.librarymanagementsystem.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public interface AdminService {

	boolean registerAdmin(Admin admin);

	Admin loginAdmin(String email, String password);

	boolean addBook(Book book);

	boolean removeBook(int id);

	boolean updateBook(Book book);

	ArrayList<Book> searchBookByTitle(String bookName);

	ArrayList<Book> searchBookByAuthor(String author);

	ArrayList<Book> searchBookByCategory(String category);

	ArrayList<Book> getBooksInfo();

	List<User> showUsers();

	List<Request> showRequests();

	boolean bookIssue(User user, Book book);

	boolean isBookReceived(User user, Book book);

}