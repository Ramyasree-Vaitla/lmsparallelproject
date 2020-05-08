package com.capgemini.librarymanagementsystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystem.database.LibraryCollectionsDB;
import com.capgemini.librarymanagementsystem.dto.AdminInfoBean;
import com.capgemini.librarymanagementsystem.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfoBean;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem.exception.LMSException;

public class AdminDAOImplement implements AdminDAO {

	@Override
	public boolean registerAdmin(AdminInfoBean adminInfoBean) {
		for (AdminInfoBean ad : LibraryCollectionsDB.ADMINS) {
			if (ad.getEmail().equals(adminInfoBean.getEmail())) {
				return false;
			}
		}
		LibraryCollectionsDB.ADMINS.add(adminInfoBean);
		return true;
	}

	@Override
	public AdminInfoBean loginAdmin(String email, String password) {
		for (AdminInfoBean adminInfoBean : LibraryCollectionsDB.ADMINS) {
			if (adminInfoBean.getEmail().equals(email) && adminInfoBean.getPassword().equals(password)) {
				return adminInfoBean;
			}
		}
		throw new LMSException("Invalid credentials");

	}

	@Override
	public boolean addBook(BookInfoBean bookInfoBean) {
		for (BookInfoBean b : LibraryCollectionsDB.BOOKS) {
			if (b.getBookId() == bookInfoBean.getBookId()) {
				return false;
			}
		}
		LibraryCollectionsDB.BOOKS.add(bookInfoBean);
		return true;
	}

	@Override
	public boolean removeBook(int id) {
		boolean removeStatus = false;
		for (int i = 0; i <= LibraryCollectionsDB.BOOKS.size() - 1; i++) {
			BookInfoBean retrievedBook = LibraryCollectionsDB.BOOKS.get(i);
			int retrievedId = retrievedBook.getBookId();
			if (id == retrievedId) {
				removeStatus = true;
				LibraryCollectionsDB.BOOKS.remove(i);
				break;
			}
		}
		return removeStatus;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean updateBook(BookInfoBean bookInfoBean) {

		for (int i = 0; i <= LibraryCollectionsDB.BOOKS.size() - 1; i++) {
			BookInfoBean retrievedBook = LibraryCollectionsDB.BOOKS.get(i);
			if (retrievedBook.getBookId() == bookInfoBean.getBookId()) {
				retrievedBook.setBookName(bookInfoBean.getBookName());
				retrievedBook.setAuthorName(bookInfoBean.getAuthorName());
				retrievedBook.setBookCategory(bookInfoBean.getBookCategory());
				return true;
			}

			else {
				throw new LMSException("Invalid Book");
			}
		}
		throw new LMSException("Book not updated");
	}

	@Override
	public ArrayList<BookInfoBean> searchBookByTitle(String bookName) {
		ArrayList<BookInfoBean> searchList = new ArrayList<BookInfoBean>();
		for (int i = 0; i <= LibraryCollectionsDB.BOOKS.size() - 1; i++) {
			BookInfoBean retrievedBook = LibraryCollectionsDB.BOOKS.get(i);
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
	public ArrayList<BookInfoBean> searchBookByAuthor(String author) {
		ArrayList<BookInfoBean> searchList = new ArrayList<BookInfoBean>();
		for (int i = 0; i <= LibraryCollectionsDB.BOOKS.size() - 1; i++) {
			BookInfoBean retrievedBook = LibraryCollectionsDB.BOOKS.get(i);
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
	public ArrayList<BookInfoBean> getBooksInfo() {
		return LibraryCollectionsDB.BOOKS;
	}

	@Override
	public ArrayList<BookInfoBean> searchBookByCategory(String category) {
		ArrayList<BookInfoBean> searchList = new ArrayList<BookInfoBean>();
		for (int i = 0; i <= LibraryCollectionsDB.BOOKS.size() - 1; i++) {
			BookInfoBean retrievedBook = LibraryCollectionsDB.BOOKS.get(i);
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
	public List<UserInfoBean> showUsers() {
		List<UserInfoBean> usersList = new ArrayList<UserInfoBean>();
		for (UserInfoBean userBean : LibraryCollectionsDB.USER) {

			userBean.getId();
			userBean.getName();
			userBean.getEmail();
			userBean.getBooksBorrowed();
			usersList.add(userBean);

		}
		return usersList;
	}

	@Override
	public List<RequestInfoBean> showRequests() {
		List<RequestInfoBean> info = new ArrayList<RequestInfoBean>();
		for (RequestInfoBean requestInfo : LibraryCollectionsDB.REQUEST) {
			requestInfo.getBookInfo();
			requestInfo.getUserInfo();
			requestInfo.isIssued();
			requestInfo.isReturned();
			info.add(requestInfo);
		}
		return info;
	}

	@Override
	public boolean bookIssue(UserInfoBean userInfoBean, BookInfoBean bookInfoBean) {
		boolean isValid = false;

		RequestInfoBean requestInfo = new RequestInfoBean();

		int noOfBooksBorrowed = userInfoBean.getBooksBorrowed();
		for (RequestInfoBean info : LibraryCollectionsDB.REQUEST) {
			if (info.getUserInfo().getId() == userInfoBean.getId()) {
				if (info.getBookInfo().getBookId() == bookInfoBean.getBookId()) {
					requestInfo = info;

					isValid = true;

				}
			}
		}

		if (isValid) {
			for (BookInfoBean info2 : LibraryCollectionsDB.BOOKS) {
				if (info2.getBookId() == bookInfoBean.getBookId()) {
					bookInfoBean = info2;
				}
			}

			for (UserInfoBean userInfo : LibraryCollectionsDB.USER) {
				if (userInfo.getId() == userInfoBean.getId()) {
					userInfoBean = userInfo;
					noOfBooksBorrowed = userInfoBean.getBooksBorrowed();

				}
			}

			if (noOfBooksBorrowed < 3) {

				boolean isRemoved = LibraryCollectionsDB.BOOKS.remove(bookInfoBean);
				if (isRemoved) {

					noOfBooksBorrowed++;
					System.out.println(noOfBooksBorrowed);
					userInfoBean.setBooksBorrowed(noOfBooksBorrowed);
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
	public boolean isBookReceived(UserInfoBean userInfoBean, BookInfoBean bookInfoBean) {
		boolean isValid = false;
		RequestInfoBean requestInfo1 = new RequestInfoBean();
		for (RequestInfoBean requestInfo : LibraryCollectionsDB.REQUEST) {

			if (requestInfo.getBookInfo().getBookId() == bookInfoBean.getBookId()
					&& requestInfo.getUserInfo().getId() == userInfoBean.getId() && requestInfo.isReturned() == true) {
				isValid = true;
				requestInfo1 = requestInfo;
			}
		}
		if (isValid) {

			bookInfoBean.setAuthorName(requestInfo1.getBookInfo().getAuthorName());
			bookInfoBean.setBookName(requestInfo1.getBookInfo().getBookName());
			LibraryCollectionsDB.BOOKS.add(bookInfoBean);
			LibraryCollectionsDB.REQUEST.remove(requestInfo1);

			for (UserInfoBean userInfo2 : LibraryCollectionsDB.USER) {
				if (userInfo2.getId() == userInfoBean.getId()) {
					userInfoBean = userInfo2;
				}
			}
			int noOfBooksBorrowed = userInfoBean.getBooksBorrowed();
			noOfBooksBorrowed--;
			userInfoBean.setBooksBorrowed(noOfBooksBorrowed);
			return true;
		}
		return false;
	}
}