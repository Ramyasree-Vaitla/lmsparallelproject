package com.capgemini.librarymanagementsystem_springrest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;



@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibraryResponseInfoBean {

	private boolean error;
	private String message;

	private UsersInfoBean usersInfoBean;
	private BookInfoBean bookInfoBean;
	private BookIssueInfoBean bookIssueInfoBean;
	private BookBorrowedInfoBean bookBorrowedInfoBean;
	private BookRequestInfoBean bookrequestInfoBean;

	private List<UsersInfoBean> usersInfoBean2;
	private List<BookInfoBean> bookInfoBean2;
	private List<BookIssueInfoBean> bookIssueInfoBean2;
	private List<BookBorrowedInfoBean> bookBorrowedInfoBean2;
	private List<BookRequestInfoBean> bookRequestInfoBean2;
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UsersInfoBean getUsersInfoBean() {
		return usersInfoBean;
	}
	public void setUsersInfoBean(UsersInfoBean usersInfoBean) {
		this.usersInfoBean = usersInfoBean;
	}
	public BookInfoBean getBookInfoBean() {
		return bookInfoBean;
	}
	public void setBookInfoBean(BookInfoBean bookInfoBean) {
		this.bookInfoBean = bookInfoBean;
	}
	public BookIssueInfoBean getBookIssueInfoBean() {
		return bookIssueInfoBean;
	}
	public void setBookIssueInfoBean(BookIssueInfoBean bookIssueInfoBean) {
		this.bookIssueInfoBean = bookIssueInfoBean;
	}
	public BookBorrowedInfoBean getBookBorrowedInfoBean() {
		return bookBorrowedInfoBean;
	}
	public void setBookBorrowedInfoBean(BookBorrowedInfoBean bookBorrowedInfoBean) {
		this.bookBorrowedInfoBean = bookBorrowedInfoBean;
	}
	public BookRequestInfoBean getBookrequestInfoBean() {
		return bookrequestInfoBean;
	}
	public void setBookrequestInfoBean(BookRequestInfoBean bookrequestInfoBean) {
		this.bookrequestInfoBean = bookrequestInfoBean;
	}
	public List<UsersInfoBean> getUsersInfoBean2() {
		return usersInfoBean2;
	}
	public void setUsersInfoBean2(List<UsersInfoBean> usersInfoBean2) {
		this.usersInfoBean2 = usersInfoBean2;
	}
	public List<BookInfoBean> getBookInfoBean2() {
		return bookInfoBean2;
	}
	public void setBookInfoBean2(List<BookInfoBean> bookInfoBean2) {
		this.bookInfoBean2 = bookInfoBean2;
	}
	public List<BookIssueInfoBean> getBookIssueInfoBean2() {
		return bookIssueInfoBean2;
	}
	public void setBookIssueInfoBean2(List<BookIssueInfoBean> bookIssueInfoBean2) {
		this.bookIssueInfoBean2 = bookIssueInfoBean2;
	}
	public List<BookBorrowedInfoBean> getBookBorrowedInfoBean2() {
		return bookBorrowedInfoBean2;
	}
	public void setBookBorrowedInfoBean2(List<BookBorrowedInfoBean> bookBorrowedInfoBean2) {
		this.bookBorrowedInfoBean2 = bookBorrowedInfoBean2;
	}
	public List<BookRequestInfoBean> getBookRequestInfoBean2() {
		return bookRequestInfoBean2;
	}
	public void setBookRequestInfoBean2(List<BookRequestInfoBean> bookRequestInfoBean2) {
		this.bookRequestInfoBean2 = bookRequestInfoBean2;
	}
	
	
}
