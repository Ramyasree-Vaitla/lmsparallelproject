package com.capgemini.librarymanagementsystem.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.capgemini.librarymanagementsystem.database.LibraryCollectionsDB;
import com.capgemini.librarymanagementsystem.dto.AdminInfoBean;
import com.capgemini.librarymanagementsystem.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfoBean;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem.exception.LMSException;
import com.capgemini.librarymanagementsystem.factory.LibraryFactory;
import com.capgemini.librarymanagementsystem.service.AdminService;
import com.capgemini.librarymanagementsystem.service.UserService;
import com.capgemini.librarymanagementsystem.validation.CollectionsValidation;

public class Test {

	public static void doReg() {
		LibraryCollectionsDB.addToDB();
		boolean flag = false;

		int regId = 0;
		String regName = null;
		long regMobile = 0;
		String regEmail = null;
		String regPassword = null;

		int regId1 = 0;
		String regName1 = null;
		long regMobile1 = 0;
		String regEmail1 = null;
		String regPassword1 = null;

		int bookId = 0;
		String bookAuthor = null;
		String bookName = null;
		String bookCategory = null;
		String bookPublisherName = null;

		CollectionsValidation collectionsValidation = new CollectionsValidation();

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		do {
			try {
				System.out.println("----------WELCOME TO LIBRARY-----------");
				System.out.println("Press 1 to Admin Page");
				System.out.println("Press 2 to Student Page");
				System.out.println("-----------------------------------");

				int i = sc.nextInt();
				switch (i) {
				case 1:
					AdminService service = LibraryFactory.getAdminService();
					do {
						try {
							System.out.println("-----------------------------------");
							System.out.println("Press 1 to Admin Register");
							System.out.println("Press 2 to Login");
							System.out.println("Press 3 to exit");
							System.out.println("-----------------------------------");
							int choice = sc.nextInt();
							switch (choice) {
							case 1:
								do {
									try {
										System.out.println("Enter ID :");
										regId = sc.nextInt();
										collectionsValidation.validatedId(regId);
										flag = true;
									} catch (InputMismatchException e) {

										System.err.println("Id should contains only digits");
										flag = false;
										sc.next();
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Name :");
										regName = sc.next();
										collectionsValidation.validatedName(regName);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Name should contains only Alphabates");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Mobile :");
										regMobile = sc.nextLong();
										collectionsValidation.validatedMobile(regMobile);
										flag = true;
									} catch (InputMismatchException e) {

										System.err.println("Mobile Number  should contains only numbers");
										flag = false;
										sc.next();
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Email :");
										regEmail = sc.next();
										collectionsValidation.validatedEmail(regEmail);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Email should be proper ");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Password :");
										regPassword = sc.next();
										collectionsValidation.validatedPassword(regPassword);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Enter correct Password ");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									} /*
										 * catch (javax.xml.bind.ValidationException e) { // TODO Auto-generated catch
										 * block e.printStackTrace(); }
										 */
								} while (!flag);
								AdminInfoBean bean = new AdminInfoBean();
								bean.setAdminId(regId);
								bean.setName(regName);
								bean.setEmail(regEmail);
								bean.setPassword(regPassword);

								boolean check = service.registerAdmin(bean);
								if (check) {
									System.out.println("Registered");
								} else {
									System.out.println("Email already exist");
								}

								break;

							case 2:
								System.out.println("Enter email :");
								String email = sc.next();
								System.out.println("Enter Password :");
								String password = sc.next();
								try {
									@SuppressWarnings("unused")
									AdminInfoBean authBean = service.loginAdmin(email, password);
									System.out.println("Logged in");

									do {

										try {
											System.out.println(
													"------------------------------------------------------------------------------------");
											System.out.println("Press 1 to Add Books");
											System.out.println("Press 2 to update");
											System.out.println("Press 3 to Search the Book by Author");
											System.out.println("Press 4 to Search the Book by Title");
											System.out.println("Press 5 to Search the Book by Category");
											System.out.println("Press 6 to remove the Books");
											System.out.println("Press 7 to Get the All The Books ");
											System.out.println("Press 8 to Book Issue");
											System.out.println("Press 9 to Show Users");
											System.out.println("Press 10 to Show Requests");
											System.out.println("Press 11 Receive Returned Book");
											System.out.println("Press 12 to main");
											System.out.println(
													"-----------------------------------------------------------------------------------");
											int choice1 = sc.nextInt();
											switch (choice1) {
											case 1:

												do {
													try {
														System.out.println("Enter Book-ID :");
														bookId = sc.nextInt();
														collectionsValidation.validatedId(bookId);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("Id should contains only digits");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												do {
													try {
														System.out.println("Enter Book Name :");
														bookName = sc.next();
														collectionsValidation.validatedName(bookName);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("Book-Name should contains only Alphabets");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												do {
													try {
														System.out.println("Enter Author :");
														bookAuthor = sc.next();
														collectionsValidation.validatedName(bookAuthor);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err
																.println("Author Name should contains only Alphabates");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												do {
													try {
														System.out.println("Enter Category :");
														bookCategory = sc.next();
														collectionsValidation.validatedName(bookCategory);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println(
																"Book-Category should contains only Alphabates");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												do {
													try {
														System.out.println("Enter PublisherName :");
														bookPublisherName = sc.next();
														collectionsValidation.validatedName(bookPublisherName);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println(
																"Book-PublisherName should contains only Alphabates");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												BookInfoBean bean1 = new BookInfoBean();
												bean1.setBookId(bookId);
												bean1.setBookName(bookName);
												bean1.setAuthorName(bookAuthor);
												bean1.setBookPublisher(bookPublisherName);
												bean1.setBookCategory(bookCategory);
												// bean1.setIssuedate(bookIssuedate);
												boolean check2 = service.addBook(bean1);
												if (check2) {
													System.out.println("Book Added");
												} else {
													System.out.println("Book already exist");
												}
												break;
											case 2:
												System.out.println("Enter the updated id :");
												int bid = sc.nextInt();
												System.out.println("enter name");
												String title = sc.next();
												System.out.println("enter author");
												String bauthor = sc.next();
												System.out.println("enter category");
												@SuppressWarnings("unused")
												String category = sc.next();
												BookInfoBean bean2 = new BookInfoBean();
												bean2.setBookId(bid);
												bean2.setBookName(title);
												bean2.setAuthorName(bauthor);
												bean2.setBookCategory(bookCategory);
												boolean updated = service.updateBook(bean2);
												if (updated) {
													System.out.println("Book is updated");
												} else {
													System.out.println("Book is not updated");
												}
												break;
											case 3:
												System.out.println("Search the book by the Author Name:");
												String author = sc.next();

												BookInfoBean bean3 = new BookInfoBean();
												bean3.setAuthorName(author);
												List<BookInfoBean> authorName = service.searchBookByAuthor(author);
												System.out.println(String.format("%-10s %-15s %-15s %-15s %s", "BookId",
														"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
												for (BookInfoBean bookBean : authorName) {
													if (bookBean != null) {
														System.out.println(
																"-----------------------------------------------------------------------------------");
														System.out.println(String.format("%-10s %-15s %-15s %-15s %s",
																bookBean.getBookId(), bookBean.getBookName(),
																bookBean.getAuthorName(), bookBean.getBookCategory(),
																bookBean.getBookPublisher()));
													} else {
														System.out.println(
																"No books are available written by this author");
													}
												}
												break;
											case 4:
												System.out.println("  Search the book by the Book_Title :");
												String bookTitle = sc.next();

												BookInfoBean bean4 = new BookInfoBean();
												bean4.setBookName(bookTitle);
												List<BookInfoBean> booktitle = service.searchBookByTitle(bookTitle);
												System.out.println(String.format("%-10s %-15s %-15s %-15s %s", "BookId",
														"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
												for (BookInfoBean bookBean : booktitle) {
													if (bookBean != null) {
														System.out.println(
																"----------------------------------------------------------------------------------");

														System.out.println(String.format("%-10s %-15s %-15s %-15s %s",
																bookBean.getBookId(), bookBean.getBookName(),
																bookBean.getAuthorName(), bookBean.getBookCategory(),
																bookBean.getBookPublisher()));
													} else {
														System.out.println("No books are available with this title.");
													}
												}
												break;
											case 5:
												System.out.println("Search the book by the Book_Category :");
												String bCategory = sc.next();

												BookInfoBean bean5 = new BookInfoBean();
												bean5.setBookCategory(bCategory);
												List<BookInfoBean> bookIds = service.searchBookByCategory(bCategory);
												System.out.println(String.format("%-10s %-15s %-15s %-15s %s", "BookId",
														"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
												for (BookInfoBean bookBean : bookIds) {
													if (bookBean != null) {
														System.out.println(
																"-----------------------------------------------------------------------------------");

														System.out.println(String.format("%-10s %-15s %-15s %-15s %s",
																bookBean.getBookId(), bookBean.getBookName(),
																bookBean.getAuthorName(), bookBean.getBookCategory(),
																bookBean.getBookPublisher()));
													} else {
														System.out.println("No books are available with this Id.");
													}
												}
												break;
											case 6:
												System.out.println("Enter the book_Id to delete :");
												int book_Id = sc.nextInt();
												if (book_Id == 0) {
													System.out.println("Enter the Valid Book_Id");
												} else {
													BookInfoBean bean6 = new BookInfoBean();
													bean6.setBookId(book_Id);
													boolean remove = service.removeBook(book_Id);
													if (remove) {
														System.out.println("The Book is removed");
													} else {
														System.out.println("The Book is not removed");
													}
												}
												break;

											case 7:
												ArrayList<BookInfoBean> info = service.getBooksInfo();
												System.out.println(String.format("%-10s %-15s %-15s %-15s %s", "BookId",
														"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
												for (BookInfoBean bookBean : info) {

													if (bookBean != null) {
														// System.out.println(bookBean.toString());
														System.out.println(
																"-------------------------------------------------------------------------------------");

														System.out.println(String.format("%-10s %-15s %-15s %-15s %s",
																bookBean.getBookId(), bookBean.getBookName(),
																bookBean.getAuthorName(), bookBean.getBookCategory(),
																bookBean.getBookPublisher()));
													} else {
														System.out.println("Books info is not present");
													}
												}
												break;
											case 8:
												UserInfoBean userBean2 = new UserInfoBean();
												BookInfoBean bookBean2 = new BookInfoBean();
												System.out.println("enter Book Id");
												int bId = sc.nextInt();
												System.out.println("enter User Id");
												int uId = sc.nextInt();

												bookBean2.setBookId(bId);
												userBean2.setId(uId);

												try {
													boolean isIssued = service.bookIssue(userBean2, bookBean2);
													if (isIssued) {
														System.out.println("Book Issued");
													} else {
														System.out.println("Book cannot be issued");
													}

												} catch (Exception e) {
													System.out.println("Invalid data request book cannot be issued");
												}
												break;
											case 9:
												try {
													System.out.println("Users of Library are :");
													System.out.println(
															"------------------------------------------------------------------------------------------------");

													List<UserInfoBean> userInfos = service.showUsers();
													System.out.println(
															String.format("%-15s %-10s %-10s %-15s %s", "UserId",
																	"UserName", "UserEmail", "UserNoOfBooksBorrowed"));
													for (UserInfoBean infos : userInfos) {
														System.out.println(String.format("%-15s %-10s %-10s %s",
																infos.getId(), infos.getName(), infos.getEmail(),
																infos.getBooksBorrowed()));
													}
												} catch (Exception e) {
													System.out
															.println("none of the users are registered in the library");
												}
												break;
											case 10:
												try {
													System.out.println("Requests for Books are :");
													System.out.println(
															"------------------------------------------------------------------------------------------------------------");

													List<RequestInfoBean> requestInfos = service.showRequests();
													System.out.println(String.format("%-15s %-10s %-10s %-15s %s",
															"BookId", "BookName", "UserId", "UserName", "BookIssued",
															"BookReturned"));
													for (RequestInfoBean info1 : requestInfos) {
														System.out.println(String.format("%-15s %-10s %-10s %-15s %s",

																info1.getBookInfo().getBookId(),
																info1.getBookInfo().getAuthorName(),
																info1.getUserInfo().getId(),
																info1.getUserInfo().getName(), info1.isIssued(),
																info1.isReturned()));

													}
												} catch (Exception e) {
													System.out.println("no book is requested");
												}
												break;
											case 11:
												System.out.println("Receive Returned Book");
												System.out.println("-----------------------");
												System.out.println("Enter Student Id");
												int user_Id = sc.nextInt();
												System.out.println("Enter Book Id");
												int book_id = sc.nextInt();

												UserInfoBean student = new UserInfoBean();
												BookInfoBean bookInfoBean = new BookInfoBean();
												student.setId(user_Id);
												;
												bookInfoBean.setBookId(book_id);
												boolean isReceive = service.isBookReceived(student, bookInfoBean);
												if (isReceive) {
													System.out.println(" Received Returned book");
												} else {
													System.out.println("Invalid ! Admin unable to receive");
												}
												break;
											case 12:
												doReg();
											default:
												System.out.println("Invalid Choice");
												break;
											}

										} catch (InputMismatchException e) {
											System.out.println("Invalid entry please provide only positive integer");
											sc.nextLine();
										}
									} while (true);
								} catch (Exception e) {
									System.out.println("Invalid credentials");
								}

								break;
							case 3:
								doReg();
								break;
							default:
								System.out.println("Invalid Choice");
								break;
							}
						} catch (InputMismatchException e) {
							System.err.println("Invalid entry please provide only positive integer");
							sc.nextLine();
						}
					} while (true);

				case 2:
					UserService service1 = LibraryFactory.getUserService();
					do {
						try {
							System.out.println("-----------------------------------");
							System.out.println("Press 1 to Student Register");
							System.out.println("Press 2 to Student Login");
							System.out.println("Press 3 to main");
							System.out.println("-----------------------------------");
							int choice = sc.nextInt();
							switch (choice) {
							case 1:
								do {
									try {
										System.out.println("Enter ID :");
										regId1 = sc.nextInt();
										collectionsValidation.validatedId(regId1);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Id should contains only digits");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Name :");
										regName1 = sc.next();
										collectionsValidation.validatedName(regName1);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Name should contains only Alphabates");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Mobile :");
										regMobile = sc.nextLong();
										collectionsValidation.validatedMobile(regMobile);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Mobile Number  should contains only numbers");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Email :");
										regEmail1 = sc.next();
										collectionsValidation.validatedEmail(regEmail1);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Email should be proper ");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Password :");
										regPassword1 = sc.next();
										collectionsValidation.validatedPassword(regPassword1);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Enter correct Password ");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								UserInfoBean bean1 = new UserInfoBean();
								bean1.setId(regId1);
								bean1.setName(regName1);
								bean1.setPhone(regMobile1);
								bean1.setEmail(regEmail1);
								bean1.setPassword(regPassword1);

								boolean check = service1.registerUser(bean1);
								if (check) {
									System.out.println("Registered");
								} else {
									System.out.println("Email already exist");
								}
								break;
							case 2:
								System.out.println("Enter email :");
								String email = sc.next();
								System.out.println("Enter Password :");
								String password = sc.next();
								try {
									@SuppressWarnings("unused")
									UserInfoBean studentBean = service1.loginUser(email, password);
									System.out.println("Logged in");
									do {
										try {
											System.out.println(
													"----------------------------------------------------------------------------------------");
											System.out.println("Press 1 to Search the Book by Author");
											System.out.println("Press 2 to Search the Book by Title");
											System.out.println("Press 3 to Search the Book by Id");
											System.out.println("Press 4 to Get the Books Information");
											System.out.println("Press 5 to Request the Book");
											System.out.println("Press 6 to Return the Book");
											System.out.println("Press 7 to main");
											System.out.println(
													"-------------------------------------------------------------------------------------------");
											int choice2 = sc.nextInt();
											switch (choice2) {
											case 1:
												System.out.println("Search the book by the Author Name :");
												String author = sc.next();

												BookInfoBean bean2 = new BookInfoBean();
												bean2.setAuthorName(author);
												List<BookInfoBean> bookauthor = service1.searchBookByAuthor(author);

												for (BookInfoBean bookBean : bookauthor) {

													if (bookBean != null) {
														System.out.println(
																"------------------------------------------------------------------------------------");
														System.out.println(String.format("%-10s %-15s %-15s %-15s %s",
																bookBean.getBookId(), bookBean.getBookName(),
																bookBean.getAuthorName(), bookBean.getBookCategory(),
																bookBean.getBookPublisher()));
													} else {
														System.out.println(
																"No books are available written by this author");
													}
												}
												break;
											case 2:
												System.out.println("Search the book by the Book_Title :");
												String book_Name = sc.next();

												BookInfoBean bean3 = new BookInfoBean();
												bean3.setBookName(book_Name);
												List<BookInfoBean> booktitle = service1.searchBookByTitle(book_Name);
												for (BookInfoBean bookBean : booktitle) {
													if (bookBean != null) {
														System.out.println(
																"-------------------------------------------------------------------------------------");
														System.out.println(String.format("%-10s %-15s %-15s %-15s %s",
																bookBean.getBookId(), bookBean.getBookName(),
																bookBean.getAuthorName(), bookBean.getBookCategory(),
																bookBean.getBookPublisher()));
													} else {
														System.out.println("No books are available with this title.");
													}
												}
												break;
											case 3:
												System.out.println("  Search the book by the Book_Category :");
												String book_Category = sc.next();

												BookInfoBean bean4 = new BookInfoBean();
												bean4.setBookCategory(book_Category);
												;
												List<BookInfoBean> bookIds = service1.searchBookByCategory(book_Category);
												for (BookInfoBean bookBean : bookIds) {
													if (bookBean != null) {
														System.out.println(
																"--------------------------------------------------------------------------------------");
														System.out.println(String.format("%-10s %-15s %-15s %-15s %s",
																bookBean.getBookId(), bookBean.getBookName(),
																bookBean.getAuthorName(), bookBean.getBookCategory(),
																bookBean.getBookPublisher()));
													} else {
														System.out.println("No books are available with this Id.");
													}
												}
												break;

											case 4:
												ArrayList<BookInfoBean> info = service1.getBooksInfo();
												for (BookInfoBean bookBean : info) {

													if (bookBean != null) {
														System.out.println(
																"-------------------------------------------------------------------------------------");
														System.out.println(String.format("%-10s %-15s %-15s %-15s %s",
																bookBean.getBookId(), bookBean.getBookName(),
																bookBean.getAuthorName(), bookBean.getBookCategory(),
																bookBean.getBookPublisher()));
													} else {
														System.out.println("Books info is not present");
													}
												}
												break;
											case 5:
												System.out.println("Enter book id");
												int bId = sc.nextInt();
												BookInfoBean bookBean = new BookInfoBean();
												bookBean.setBookId(bId);

												System.out.println("Enter user id");
												int userId = sc.nextInt();
												UserInfoBean userBean = new UserInfoBean();
												userBean.setId(userId);
												;

												try {
													RequestInfoBean requestInfoBean = service1.bookRequest(userBean, bookBean);
													System.out.println("Request placed to admin");
													System.out.println(
															"-----------------------------------------------------------------------------------------");
													System.out.println(String.format("%-10s %-15s %-10s %s", "UserId",
															"UserName", "BookId", "BookName"));
													System.out.println(String.format("%-10s %-15s %-10s %s",
															requestInfoBean.getUserInfo().getId(),
															requestInfoBean.getUserInfo().getName(),
															requestInfoBean.getBookInfo().getBookId(),
															requestInfoBean.getBookInfo().getBookName()));
												} catch (Exception e) {

													System.out.println("Enter valid data or Invalid Request");
												}
												break;
											case 6:

												System.out.println("Returning a book:");
												System.out.println("------------------");
												System.out.println("Enter User Id :");
												int user = sc.nextInt();
												System.out.println("Enter Book Id : ");
												int book = sc.nextInt();
												UserInfoBean userBean7 = new UserInfoBean();
												userBean7.setId(user);
												;
												BookInfoBean bookBean7 = new BookInfoBean();
												bookBean7.setBookId(book);

												try {
													RequestInfoBean requestInfo = service1.bookReturn(userBean7, bookBean7);
													System.out.println("Book is Returning to Admin");
													System.out.println(
															"-------------------------------------------------------------------------------------------");
													System.out.println(String.format("%-10s, %-10s,%-s",
															requestInfo.getUserInfo().getId(),
															requestInfo.getBookInfo().getBookId(),
															requestInfo.isReturned()));

												} catch (Exception e) {
													System.out.println("Invalid Return");
												}

												break;
											case 7:
												doReg();
											default:
												break;
											}
										} catch (InputMismatchException e) {
											System.out.println("Invalid entry please provide only positive integer");
											sc.nextLine();
										}
									} while (true);
								} catch (Exception e) {
									System.out.println("Invalid Credentials");
								}
								break;

							case 3:
								doReg();
							default:
								System.out.println("Invalid Choice");
								break;
							}
						} catch (InputMismatchException e) {
							System.err.println("Invalid entry please provide only positive integer");
							sc.nextLine();
						}
					} while (true);

				}
				System.err.println("choice must be in 1 or 2");
			} catch (InputMismatchException e) {
				System.err.println("Invalid entry please provide only positive integer");
				sc.nextLine();
			}
		} while (true);
	}
}
