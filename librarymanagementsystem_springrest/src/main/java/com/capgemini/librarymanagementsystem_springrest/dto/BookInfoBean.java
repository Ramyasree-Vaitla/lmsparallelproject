package com.capgemini.librarymanagementsystem_springrest.dto;

import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "bookbean")
@JsonRootName("Book")
@SequenceGenerator(name = "seq3", initialValue = 101, allocationSize = 100)
public class BookInfoBean implements Serializable {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq3")
	private int bId;
	@Column
	private String bookName;
	@Column
	private String author;
	@Column
	private String category;
	@Column
	private String publisher;

	/*
	 * 
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "books") private
	 * List<BookIssueDetails> issueDetails;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "books") private
	 * List<RequestDetails> requests;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "books") private
	 * List<BorrowedBooks> borrowed;
	 * 
	 */

}
