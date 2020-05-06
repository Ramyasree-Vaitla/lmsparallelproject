package com.capgemini.librarymanagementsystem.database;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public class LibraryDB {
	public static final ArrayList<Book> BOOKS = new ArrayList<Book>();
	public static final ArrayList<User> USER = new ArrayList<User>();
	public static final ArrayList<Admin> ADMINS = new ArrayList<Admin>();
	public static final ArrayList<Request> REQUEST = new ArrayList<Request>();

	public static void addToDB() {

		ADMINS.add(new Admin(123456, "ramyasree@gmail.com", "ramyasree", "ramyasree@123"));
		ADMINS.add(new Admin(234567, "tejasree@gmail.com", "teja@gmail.com", "teja@123"));

		// User(111111,"ramya","ramya@gmail.com","Ramya@123","cse",7788997788,07/08/2020,02/02/2020);

		BOOKS.add(new Book(100101, "java", "james", "gosling", "coding"));
		BOOKS.add(new Book(101102, "history", "tom", "henry fileld", "world"));
		BOOKS.add(new Book(102103, "angular", "misko", "adam", "js"));
		BOOKS.add(new Book(103104, "computers", "charles", "aborns", "programing"));

	}
}