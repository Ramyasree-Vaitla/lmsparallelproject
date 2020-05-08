package com.capgemini.librarymanagementsystem_springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capgemini.librarymanagementsystem_springrest.dto.BookBorrowedInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.BookRequestInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.LibraryResponseInfoBean;
import com.capgemini.librarymanagementsystem_springrest.dto.UsersInfoBean;
import com.capgemini.librarymanagementsystem_springrest.service.AdminService;
import com.capgemini.librarymanagementsystem_springrest.service.AdminUserService;
import com.capgemini.librarymanagementsystem_springrest.service.UserService;

public class SpringRestController {

	@Autowired
	private AdminService service1;
	private AdminUserService service2;
	private UserService service3;

	@PostMapping(path = "/addUser", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })

	public LibraryResponseInfoBean addUser(@RequestBody UsersInfoBean bean) {
		boolean isAdded = service2.register(bean);
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();
		if (isAdded) {
			response.setMessage("record inserted");
		} else {
			response.setError(true);
			response.setMessage("unable to add");
		}
		return response;
	}

	@PostMapping(path = "/addBook", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean addBook(@RequestBody BookInfoBean bean) {
		boolean isBookAdded = service1.addBook(bean);
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();
		if (isBookAdded) {
			response.setMessage("Book added succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be added");
		}
		return response;

	}

	@PutMapping(path = "/bookUpdate", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean updateBook(@RequestBody BookInfoBean bean) {
		boolean isBookUpdated = service1.updateBook(bean);
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();
		if (isBookUpdated) {
			response.setMessage("Book updated succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be updated");
		}
		return response;

	}

	@PostMapping(path = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean authentication(@RequestBody String email, String password) {
		UsersInfoBean userLogin = service2.login(email, password);
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();
		if (userLogin != null) {
			response.setMessage("Login succesfully");
		} else {
			response.setError(true);
			response.setMessage("Invalid credentials,Please try again");
		}
		return response;
	}

	@DeleteMapping(path = "/removeBook/{bookId} ", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean deleteBook(@PathVariable(name = "bookId") int bookId) {
		boolean isBookDeleted = service1.removeBook(bookId);
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();
		if (isBookDeleted) {
			response.setMessage("Book deleted succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book not deleted");
		}
		return response;
	}

	@GetMapping(path = "/BooksInfo", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean getBookInfo() {
		List<BookInfoBean> getInfo = service2.getBooksInfo();
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();
		if (getInfo != null && !getInfo.isEmpty()) {
			response.setMessage("Books found");
			response.setBookInfoBean2(getInfo);
		} else {
			response.setError(true);
			response.setMessage("They are no books in the Library");
		}
		return response;
	}

	@GetMapping(path = "/BooksByName", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean getBookByName(String bookTitle) {
		List<BookInfoBean> bean = service2.searchBookByTitle(bookTitle);
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();
		if (bean != null && !bean.isEmpty()) {
			response.setMessage("Books found");
			response.setBookInfoBean2(bean);
		} else {
			response.setError(true);
			response.setMessage("They are no books in the Library");
		}
		return response;
	}

	@GetMapping(path = "/BooksByAuthor", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean getBookByAuthor(String author) {
		List<BookInfoBean> bean = service2.searchBookByAuthor(author);
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();
		if (bean != null && !bean.isEmpty()) {
			response.setMessage("Books found");
			response.setBookInfoBean2(bean);
		} else {
			response.setError(true);
			response.setMessage("They are no books in the Library");
		}
		return response;
	}

	@GetMapping(path = "/BooksById", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean getBookById(int bId) {
		List<BookInfoBean> bean = service2.searchBookById(bId);
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();
		if (bean != null && !bean.isEmpty()) {
			response.setMessage("Books found");
			response.setBookInfoBean2(bean);
		} else {
			response.setError(true);
			response.setMessage("They are no books in the Library");
		}
		return response;
	}

	@PostMapping(path = "/bookIssue", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean issueBook(@RequestBody int id, int bookId) {
		boolean isBookIssued = service1.issueBook(id, bookId);
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();
		if (isBookIssued) {
			response.setMessage("Book issued succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be issued");
		}
		return response;
	}

	@PostMapping(path = "/returnBook", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean returnBook(@RequestBody int bookId, int id, String status) {
		boolean isBookReturned = service3.returnBook(bookId, id, status);
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();
		if (isBookReturned) {
			response.setMessage("Book returned succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be returned");
		}
		return response;
	}

	@PostMapping(path = "/requestBook", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean requestBook(@RequestBody int bookId, int id) {
		boolean isBookRequested = service3.request(bookId, id);
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();
		if (isBookRequested) {
			response.setMessage("Book requested succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be requested");
		}
		return response;
	}

	@GetMapping(path = "/showRequests", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean showRequests() {
		List<BookRequestInfoBean> detailList = service1.showRequests();
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();

		if (detailList != null && !detailList.isEmpty()) {
			response.setBookRequestInfoBean2(detailList);
		} else {
			response.setError(true);
			response.setMessage("They are no requests");
		}
		return response;
	}

	@GetMapping(path = "/showIssuedBooks", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean showIssuedBooks() {
		List<BookIssueInfoBean> issueList = service1.showIssuedBooks();
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();

		if (issueList != null && !issueList.isEmpty()) {
			response.setBookIssueInfoBean2(issueList);
		} else {
			response.setError(true);
			response.setMessage("No Books are Issued");
		}
		return response;
	}

	@GetMapping(path = "/showUsers", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean showUsers() {
		List<UsersInfoBean> usersList = service1.showUsers();
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();

		if (usersList != null && !usersList.isEmpty()) {
			response.setUsersInfoBean2(usersList);
		} else {
			response.setError(true);
			response.setMessage("They are no Users");
		}
		return response;
	}

	@PutMapping(path = "/updatePassword", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean updatePassord(int id, String password, String newPassword, String role) {
		boolean isUpdated = service2.updatePassword(id, password, newPassword, role);
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();

		if (isUpdated) {
			response.setMessage("Password updated successfully");
		} else {
			response.setError(true);
			response.setMessage("Password is not updated");
		}
		return response;
	}

	@GetMapping(path = "/getBorrowedBooks", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponseInfoBean getBorrowedBooks(@RequestBody int id) {
		List<BookBorrowedInfoBean> borrowed = service3.borrowedBook(id);
		LibraryResponseInfoBean response = new LibraryResponseInfoBean();

		if (borrowed != null && !borrowed.isEmpty()) {
			response.setBookBorrowedInfoBean2(borrowed);
		} else {
			response.setError(true);
			response.setMessage("There are no borrowed  books");
		}
		return response;
	}

}
