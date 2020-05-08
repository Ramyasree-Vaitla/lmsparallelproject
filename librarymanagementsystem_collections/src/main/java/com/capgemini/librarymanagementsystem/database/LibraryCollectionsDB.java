package com.capgemini.librarymanagementsystem.database;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystem.dto.AdminInfoBean;
import com.capgemini.librarymanagementsystem.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfoBean;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;

public class LibraryCollectionsDB {
	public static final ArrayList<BookInfoBean> BOOKS = new ArrayList<BookInfoBean>();
	public static final ArrayList<UserInfoBean> USER = new ArrayList<UserInfoBean>();
	public static final ArrayList<AdminInfoBean> ADMINS = new ArrayList<AdminInfoBean>();
	public static final ArrayList<RequestInfoBean> REQUEST = new ArrayList<RequestInfoBean>();

	public static void addToDB() {

		ADMINS.add(new AdminInfoBean(123456, "ramyasree@gmail.com", "ramyasree", "ramyasree@123"));
		//ADMINS.add(new AdminInfoBean(234567, "tejasree@gmail.com", "tejasree", "Tejasree@123"));

		BOOKS.add(new BookInfoBean(151617, "java", "james", "gosling", "coding"));
		BOOKS.add(new BookInfoBean(171819, "WingsOfFire", "AbdulKalam", "Universities", "AutoBiography"));
		BOOKS.add(new BookInfoBean(192021, "angular", "misko", "adam", "js"));
		BOOKS.add(new BookInfoBean(212223, "jpa", "charles", "sunmicrosystem", "technical"));
		BOOKS.add(new BookInfoBean(232425, "junglebook", "rudyard", "macmillan", "stories"));
	}
}