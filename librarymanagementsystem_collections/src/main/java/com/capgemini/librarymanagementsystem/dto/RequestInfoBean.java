package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class RequestInfoBean implements Serializable {

	private BookInfoBean bookInfo;
	private UserInfoBean userInfo;
	private boolean isIssued;
	private boolean isReturned;
	private LocalDate issuedDate;
	private LocalDate returnedDate;

	public BookInfoBean getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(BookInfoBean bookInfo) {
		this.bookInfo = bookInfo;
	}

	public UserInfoBean getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoBean userInfo) {
		this.userInfo = userInfo;
	}

	public boolean isIssued() {
		return isIssued;
	}

	public void setIssued(boolean isIssued) {
		this.isIssued = isIssued;
	}

	public boolean isReturned() {
		return isReturned;
	}

	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	public LocalDate getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}

	public LocalDate getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(LocalDate returnedDate) {
		this.returnedDate = returnedDate;
	}

}
