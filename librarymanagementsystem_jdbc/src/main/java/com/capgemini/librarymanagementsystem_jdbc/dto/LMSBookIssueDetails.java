package com.capgemini.librarymanagementsystem_jdbc.dto;

import java.util.Date;

public class LMSBookIssueDetails {

	private int bId;
	private int uId;
	private Date issueDate;
	private Date returnDate;

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
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

	/*
	 * @Override public String toString() { return
	 * String.format("%-10s %-10s %-10s %s", bookId, userId, issueDate, returnDate);
	 * }
	 */
}
