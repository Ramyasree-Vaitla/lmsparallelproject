package com.capgemini.librarymanagementsystem_jdbc.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.capgemini.librarymanagementsystem_jdbc.exception.LMSException;

public class JDBCUtility {

	private static Connection connection = null;

	public static Connection getConnection() throws LMSException {

		File file = null;
		FileInputStream inputStream = null;

		file = new File("db.properties");
		try {
			inputStream = new FileInputStream(file);

			Properties properties = new Properties();
			properties.load(inputStream);

			String path = properties.getProperty("path");
			String dburl = properties.getProperty("dburl");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");

			Class.forName(path);
			connection = DriverManager.getConnection(dburl, user, password);

		} catch (FileNotFoundException e) {
			throw new LMSException("File not exists");
		} catch (IOException e) {
			throw new LMSException("Unable to read the data from the file");
		} catch (ClassNotFoundException e) {
			throw new LMSException("Class not loaded");
		} catch (SQLException e) {
			throw new LMSException("Connection issue");
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new LMSException("Unable to close the file");
			}
		}
		return connection;
	}
}
