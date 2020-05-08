package com.capgemini.librarymanagementsystem_springrest.exceptions;

@SuppressWarnings("serial")
public class LMSException extends RuntimeException {

	public  LMSException(String msg) {
		super(msg);
	}
}
