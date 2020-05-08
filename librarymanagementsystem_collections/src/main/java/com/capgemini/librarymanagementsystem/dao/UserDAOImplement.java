package com.capgemini.librarymanagementsystem.dao;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystem.database.LibraryCollectionsDB;
import com.capgemini.librarymanagementsystem.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfoBean;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem.exception.LMSException;

public class UserDAOImplement implements UserDAO {

	@Override
	public boolean registerUser(UserInfoBean userInfoBean) {
		for (UserInfoBean u : LibraryCollectionsDB.USER) {
			if (u.getEmail().equals(userInfoBean.getEmail())) {
				return false;
			}
		}
		LibraryCollectionsDB.USER.add(userInfoBean);
		return true;
	}

	@Override
	public UserInfoBean loginUser(String email, String password) {
		for (UserInfoBean userInfoBean : LibraryCollectionsDB.USER) {
			if (userInfoBean.getEmail().equals(email) && userInfoBean.getPassword().equals(password)) {
				return userInfoBean;
			}
		}
		throw new LMSException("Invalid Credentials");
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
			throw new LMSException("Book is not found");
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
			throw new LMSException("Book is not found");
		} else {
			return searchList;
		}
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
	public ArrayList<BookInfoBean> getBooksInfo() {
		return LibraryCollectionsDB.BOOKS;
	}

	@Override
	public RequestInfoBean bookRequest(UserInfoBean userInfoBean, BookInfoBean bookInfoBean) {
		boolean flag = false, isRequestExists = false;
		RequestInfoBean requestInfo = new RequestInfoBean();
		UserInfoBean userInfo2 = new UserInfoBean();
		BookInfoBean bookInfo2 = new BookInfoBean();

		for (RequestInfoBean requestInfo2 : LibraryCollectionsDB.REQUEST) {
			if (bookInfoBean.getBookId() == requestInfo2.getBookInfo().getBookId()) {
				isRequestExists = true;

			}

		}

		if (!isRequestExists) {
			for (UserInfoBean userBean : LibraryCollectionsDB.USER) {
				if (userInfoBean.getId() == userBean.getId()) {
					for (BookInfoBean book1 : LibraryCollectionsDB.BOOKS) {
						if (book1.getBookId() == book1.getBookId()) {
							userInfo2 = userBean;
							bookInfo2 = book1;
							flag = true;
						}
					}
				}
			}
			if (flag == true) {
				requestInfo.setBookInfo(bookInfo2);
				requestInfo.setUserInfo(userInfo2);
				;
				LibraryCollectionsDB.REQUEST.add(requestInfo);
				return requestInfo;
			}

		}

		throw new LMSException("Invalid request or you cannot request that book");

	}

	@Override
	public RequestInfoBean bookReturn(UserInfoBean userInfoBean, BookInfoBean bookInfoBean) {
		for (RequestInfoBean requestInfo : LibraryCollectionsDB.REQUEST) {

			if (requestInfo.getBookInfo().getBookId() == bookInfoBean.getBookId()
					&& requestInfo.getUserInfo().getId() == userInfoBean.getId() && requestInfo.isIssued() == true) {

				System.out.println("Returning Issued book only");
				requestInfo.setReturned(true);

				return requestInfo;
			}

		}

		throw new LMSException("Invalid return ");
	}

}