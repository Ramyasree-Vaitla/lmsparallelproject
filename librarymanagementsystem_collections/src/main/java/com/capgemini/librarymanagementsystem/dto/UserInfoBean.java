package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UserInfoBean implements Serializable {
	private int id = (int) Math.random();
	private String name;
	private String email;
	private String password;
	private String department;
	private long phone;
	private Date issueDate;
	private Date returnDate;
	private int booksBorrowed;

	public UserInfoBean() {

	}

	public UserInfoBean(int id, String name, String email, String password, String department, long phone, Date issueDate,
			Date returnDate, int booksBorrowed) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.department = department;
		this.phone = phone;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.booksBorrowed = booksBorrowed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getBooksBorrowed() {
		return booksBorrowed;
	}

	public void setBooksBorrowed(int booksBorrowed) {
		this.booksBorrowed = booksBorrowed;
	}

	@Override
	public String toString() {
		return "User [userId=" + id + ", userName=" + name + ", userEmailId=" + email + ", userPassword=" + password
				+ ", Department=" + department + ", " + "phoneNumber=" + phone + " + IssueDate=" + issueDate
				+ ",ReturnDate=" + returnDate + ", noOfBooksBorrowed=" + booksBorrowed + "]";

	}

}