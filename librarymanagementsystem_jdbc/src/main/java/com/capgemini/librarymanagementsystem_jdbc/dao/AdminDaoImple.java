package com.capgemini.librarymanagementsystem_jdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssueInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.RequestBookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.exception.LMSException;

public class AdminDaoImple implements AdminDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;

	@Override
	public boolean addBook(BookInfoBean book) {
		// TODO Auto-generated method stub

		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("addBookQuery"));) {
				pstmt.setInt(1, book.getBId());
				pstmt.setString(2, book.getBookName());
				pstmt.setString(3, book.getAuthor());
				pstmt.setString(4, book.getCategory());
				pstmt.setString(5, book.getPublisher());
				// pstmt.setInt(6, book.getCopies());
				int count = pstmt.executeUpdate();
				if (count != 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean bookIssue(int bId, int uId) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pst = conn.prepareStatement(pro.getProperty("issueBookQuery1"));) {
				pst.setInt(1, bId);
				rs = pst.executeQuery();
				if (rs.next()) {
					try (PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("issueBookQuery2"))) {
						pstmt.setInt(1, uId);
						pstmt.setInt(2, bId);
						pstmt.setInt(3, uId);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							try (PreparedStatement pstmt1 = conn
									.prepareStatement(pro.getProperty("issueBookQuery3"));) {
								pstmt1.setInt(1, bId);
								pstmt1.setInt(2, uId);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Calendar cal = Calendar.getInstance();
								String issueDate = sdf.format(cal.getTime());
								pstmt1.setDate(3, java.sql.Date.valueOf(issueDate));
								cal.add(Calendar.DAY_OF_MONTH, 7);
								String returnDate = sdf.format(cal.getTime());
								pstmt1.setDate(4, java.sql.Date.valueOf(returnDate));
								int count = pstmt1.executeUpdate();
								if (count != 0) {
									try (PreparedStatement pstmt2 = conn
											.prepareStatement(pro.getProperty("issueBookQuery4"))) {
										pstmt2.setInt(1, uId);
										pstmt2.setInt(2, bId);
										pstmt2.setInt(3, uId);
										int isBorrowed = pstmt2.executeUpdate();
										if (isBorrowed != 0) {
											return true;
										} else {
											return false;
										}
									}
								} else {
									throw new LMSException("Book Not issued");
								}
							}
						} else {
							throw new LMSException("The respective user have not placed any request");
						}
					}
				} else {
					throw new LMSException("There is no book exist with bookId" + bId);
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateBook(BookInfoBean book) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("updateBookQuery"));) {
				pstmt.setString(1, book.getBookName());
				pstmt.setInt(2, book.getBId());
				int count = pstmt.executeUpdate();
				if (count != 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean removeBook(int bId) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("removeBookQuery"));) {
				pstmt.setInt(1, bId);
				int count = pstmt.executeUpdate();
				if (count != 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public List<UserInfoBean> showUsers() {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					Statement stmt = (Statement) conn.createStatement();
					ResultSet rs = stmt.executeQuery(pro.getProperty("showUsersQuery"));) {
				LinkedList<UserInfoBean> beans = new LinkedList<UserInfoBean>();
				while (rs.next()) {
					UserInfoBean bean = new UserInfoBean();
					bean.setuId(rs.getInt("uId"));
					bean.setFirstName(rs.getString("firstName"));
					bean.setLastName(rs.getString("lastName"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("password"));
					bean.setMobile(rs.getLong("mobile"));
					bean.setRole(rs.getString("role"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<RequestBookInfoBean> showRequests() {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					Statement stmt = (Statement) conn.createStatement();
					ResultSet rs = stmt.executeQuery(pro.getProperty("showRequestsQuery"));) {
				LinkedList<RequestBookInfoBean> beans = new LinkedList<RequestBookInfoBean>();
				while (rs.next()) {
					RequestBookInfoBean bean = new RequestBookInfoBean();
					bean.setuId(rs.getInt("uId"));
					bean.setFullName(rs.getString("fullName"));
					bean.setbId(rs.getInt("bId"));
					bean.setBookName(rs.getString("bookName"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BookIssueInfoBean> showIssuedBooks() {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					Statement stmt = (Statement) conn.createStatement();
					ResultSet rs = stmt.executeQuery(pro.getProperty("showIssuedBooksQuery"));) {
				LinkedList<BookIssueInfoBean> beans = new LinkedList<BookIssueInfoBean>();
				while (rs.next()) {
					BookIssueInfoBean bean = new BookIssueInfoBean();
					bean.setbId(rs.getInt("bId"));
					bean.setuId(rs.getInt("uId"));
					bean.setIssueDate(rs.getDate("issueDate"));
					bean.setReturnDate(rs.getDate("returnDate"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}

	}

}
