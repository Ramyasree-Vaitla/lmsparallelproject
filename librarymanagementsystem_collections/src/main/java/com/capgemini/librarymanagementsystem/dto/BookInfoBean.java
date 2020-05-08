package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookInfoBean implements Serializable {
	private int bookId = (int) Math.random();
	private String bookName;
	private String authorName;
	private String bookPublisher;
	private String bookCategory;

	public BookInfoBean() {

	}

	public BookInfoBean(int bookId, String bookName, String authorName, String bookPublisher, String bookCategory) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.bookPublisher = bookPublisher;
		this.bookCategory = bookCategory;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", authorName=" + authorName + ", Publisher="
				+ bookPublisher + ", Catergory=" + bookCategory + "]";
	}
}