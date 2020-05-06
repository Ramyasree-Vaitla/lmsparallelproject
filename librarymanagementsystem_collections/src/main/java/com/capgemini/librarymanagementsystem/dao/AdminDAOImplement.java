package com.capgemini.librarymanagementsystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystem.database.LibraryDB;
import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;
import com.capgemini.librarymanagementsystem.exception.LMSException;

public class AdminDAOImplement implements AdminDAO {

	@Override
	public boolean registerAdmin(Admin admin) {
		for (Admin ad : LibraryDB.ADMINS) {
			if (ad.getEmail().equals(admin.getEmail())) {
				return false;
			}
		}
		LibraryDB.ADMINS.add(admin);
		return true;
	}

	@Override
	public Admin loginAdmin(String email, String password) {
		for (Admin admin : LibraryDB.ADMINS) {
			if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
				return admin;
			}
		}
		throw new LMSException("Invalid credentials");

	}

	@Override
	public boolean addBook(Book book) {
		for (Book b : LibraryDB.BOOKS) {
			if (b.getBookId() == book.getBookId()) {
				return false;
			}
		}
		LibraryDB.BOOKS.add(book);
		return true;
	}

	@Override
	public boolean removeBook(int id) {
		boolean removeStatus = false;
		for (int i = 0; i <= LibraryDB.BOOKS.size() - 1; i++) {
			Book retrievedBook = LibraryDB.BOOKS.get(i);
			int retrievedId = retrievedBook.getBookId();
			if (id == retrievedId) {
				removeStatus = true;
				LibraryDB.BOOKS.remove(i);
				break;
			}
		}
		return removeStatus;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean updateBook(Book book) {

		for (int i = 0; i <= LibraryDB.BOOKS.size() - 1; i++) {
			Book retrievedBook = LibraryDB.BOOKS.get(i);
			if (retrievedBook.getBookId() == book.getBookId()) {
				retrievedBook.setBookName(book.getBookName());
				retrievedBook.setAuthorName(book.getAuthorName());
				retrievedBook.setBookCategory(book.getBookCategory());
				return true;
			}

			else {
				throw new LMSException("Invalid Book");
			}
		}
		throw new LMSException("Book not updated");
	}

	@Override
	public ArrayList<Book> searchBookByTitle(String bookName) {
		ArrayList<Book> searchList = new ArrayList<Book>();
		for (int i = 0; i <= LibraryDB.BOOKS.size() - 1; i++) {
			Book retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedBookName = retrievedBook.getBookName();
			if (bookName.equals(retrievedBookName)) {
				searchList.add(retrievedBook);
				return searchList;
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}

	}

	@Override
	public ArrayList<Book> searchBookByAuthor(String author) {
		ArrayList<Book> searchList = new ArrayList<Book>();
		for (int i = 0; i <= LibraryDB.BOOKS.size() - 1; i++) {
			Book retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedBookAuthor = retrievedBook.getAuthorName();
			if (author.equals(retrievedBookAuthor)) {
				searchList.add(retrievedBook);
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}

	}

	@Override
	public ArrayList<Book> getBooksInfo() {
		return LibraryDB.BOOKS;
	}

	@Override
	public ArrayList<Book> searchBookByCategory(String category) {
		ArrayList<Book> searchList = new ArrayList<Book>();
		for (int i = 0; i <= LibraryDB.BOOKS.size() - 1; i++) {
			Book retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedCategory = retrievedBook.getBookCategory();
			if (category.equals(retrievedCategory)) {
				searchList.add(retrievedBook);
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}
	}

	@Override
	public List<User> showUsers() {
		List<User> usersList = new ArrayList<User>();
		for (User userBean : LibraryDB.USER) {

			userBean.getId();
			userBean.getName();
			userBean.getEmail();
			userBean.getBooksBorrowed();
			usersList.add(userBean);

		}
		return usersList;
	}

	@Override
	public List<Request> showRequests() {
		List<Request> info = new ArrayList<Request>();
		for (Request requestInfo : LibraryDB.REQUEST) {
			requestInfo.getBookInfo();
			requestInfo.getUserInfo();
			requestInfo.isIssued();
			requestInfo.isReturned();
			info.add(requestInfo);
		}
		return info;
	}

	@Override
	public boolean bookIssue(User user, Book book) {
		boolean isValid = false;

		Request requestInfo = new Request();

		int noOfBooksBorrowed = user.getBooksBorrowed();
		for (Request info : LibraryDB.REQUEST) {
			if (info.getUserInfo().getId() == user.getId()) {
				if (info.getBookInfo().getBookId() == book.getBookId()) {
					requestInfo = info;

					isValid = true;

				}
			}
		}

		if (isValid) {
			for (Book info2 : LibraryDB.BOOKS) {
				if (info2.getBookId() == book.getBookId()) {
					book = info2;
				}
			}

			for (User userInfo : LibraryDB.USER) {
				if (userInfo.getId() == user.getId()) {
					user = userInfo;
					noOfBooksBorrowed = user.getBooksBorrowed();

				}
			}

			if (noOfBooksBorrowed < 3) {

				boolean isRemoved = LibraryDB.BOOKS.remove(book);
				if (isRemoved) {

					noOfBooksBorrowed++;
					System.out.println(noOfBooksBorrowed);
					user.setBooksBorrowed(noOfBooksBorrowed);
					// DataBase.REQUESTDB.remove(requestInfo);
					requestInfo.setIssued(true);
					return true;
				} else {
					throw new LMSException("Book can't be borrowed");
				}

			} else {
				throw new LMSException("Student Exceeds maximum limit");
			}

		} else {
			throw new LMSException("Book data or Student data is incorrect");
		}
	}

	@Override
	public boolean isBookReceived(User user, Book book) {
		boolean isValid = false;
		Request requestInfo1 = new Request();
		for (Request requestInfo : LibraryDB.REQUEST) {

			if (requestInfo.getBookInfo().getBookId() == book.getBookId()
					&& requestInfo.getUserInfo().getId() == user.getId() && requestInfo.isReturned() == true) {
				isValid = true;
				requestInfo1 = requestInfo;
			}
		}
		if (isValid) {

			book.setAuthorName(requestInfo1.getBookInfo().getAuthorName());
			book.setBookName(requestInfo1.getBookInfo().getBookName());
			LibraryDB.BOOKS.add(book);
			LibraryDB.REQUEST.remove(requestInfo1);

			for (User userInfo2 : LibraryDB.USER) {
				if (userInfo2.getId() == user.getId()) {
					user = userInfo2;
				}
			}
			int noOfBooksBorrowed = user.getBooksBorrowed();
			noOfBooksBorrowed--;
			user.setBooksBorrowed(noOfBooksBorrowed);
			return true;
		}
		return false;
	}
}