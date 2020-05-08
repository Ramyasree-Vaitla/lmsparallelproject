package com.capgemini.librarymanagementsystem_jdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem_jdbc.exception.LMSException;

public class AdminUserDaoImple implements AdminUserDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;

	@Override
	public boolean register(UserInfoBean user) {
		// TODO Auto-generated method stub

		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("registerQuery"))) {
				pstmt.setInt(1, user.getuId());
				pstmt.setString(2, user.getFirstName());
				pstmt.setString(3, user.getLastName());
				pstmt.setString(4, user.getEmail());
				pstmt.setString(5, user.getPassword());
				pstmt.setLong(6, user.getMobile());
				pstmt.setString(7, user.getRole());
				int count = pstmt.executeUpdate();
				if (user.getEmail().isEmpty() && count == 0) {
					return false;
				} else {
					return true;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public UserInfoBean login(String email, String password) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("loginQuery"));) {
				pstmt.setString(1, email);
				pstmt.setString(2, password);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					UserInfoBean bean = new UserInfoBean();
					bean.setuId(rs.getInt("uId"));
					bean.setFirstName(rs.getString("firstName"));
					bean.setLastName(rs.getString("lastName"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("password"));
					bean.setMobile(rs.getLong("mobile"));
					bean.setRole(rs.getString("role"));
					return bean;
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pst = conn.prepareStatement(pro.getProperty("updatePasswordQuery1"))) {
				pst.setString(1, email);
				pst.setString(2, role);
				rs = pst.executeQuery();
				if (rs.next()) {
					try (PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("updatePasswordQuery2"));) {
						pstmt.setString(1, newPassword);
						pstmt.setString(2, email);
						pstmt.setString(3, password);
						int count = pstmt.executeUpdate();
						if (count != 0) {
							return true;
						} else {
							return false;
						}
					}
				} else {
					throw new LMSException("User doesnt exist");
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public List<BookInfoBean> getBooksInfo() {
		// TODO Auto-generated method stub

		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					Statement stmt = (Statement) conn.createStatement();) {
				rs = stmt.executeQuery(pro.getProperty("getBooksInfoQuery"));
				LinkedList<BookInfoBean> beans = new LinkedList<BookInfoBean>();
				while (rs.next()) {
					BookInfoBean bean = new BookInfoBean();
					bean.setBId(rs.getInt("bId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthor(rs.getString("author"));
					bean.setCategory(rs.getString("category"));
					bean.setPublisher(rs.getString("publisher"));
					// bean.setCopies(rs.getInt("copies"));
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
	public List<BookInfoBean> searchBookById(int bId) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("searchBookByIdQuery"));) {
				pstmt.setInt(1, bId);
				rs = pstmt.executeQuery();
				LinkedList<BookInfoBean> beans = new LinkedList<BookInfoBean>();
				while (rs.next()) {
					BookInfoBean bean = new BookInfoBean();
					bean.setBId(rs.getInt("bId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthor(rs.getString("author"));
					bean.setCategory(rs.getString("category"));
					bean.setPublisher(rs.getString("publisher"));
					// bean.setCopies(rs.getInt("copies"));
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
	public List<BookInfoBean> searchBookByTitle(String bookName) {
		// TODO Auto-generated method stub

		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("searchBookByTitle"));) {
				pstmt.setString(1, bookName);
				rs = pstmt.executeQuery();
				LinkedList<BookInfoBean> beans = new LinkedList<BookInfoBean>();
				while (rs.next()) {
					BookInfoBean bean = new BookInfoBean();
					bean.setBId(rs.getInt("bId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthor(rs.getString("author"));
					bean.setCategory(rs.getString("category"));
					bean.setPublisher(rs.getString("publisher"));
					// bean.setCopies(rs.getInt("copies"));
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
	public List<BookInfoBean> searchBookByAuthor(String author) {
		// TODO Auto-generated method stub
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("searchBookByAuthor"));) {
				pstmt.setString(1, author);
				rs = pstmt.executeQuery();
				LinkedList<BookInfoBean> beans = new LinkedList<BookInfoBean>();
				while (rs.next()) {
					BookInfoBean bean = new BookInfoBean();
					bean.setBId(rs.getInt("bId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthor(rs.getString("author"));
					bean.setCategory(rs.getString("category"));
					bean.setPublisher(rs.getString("publisher"));
					// bean.setCopies(rs.getInt("copies"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

	}

}
