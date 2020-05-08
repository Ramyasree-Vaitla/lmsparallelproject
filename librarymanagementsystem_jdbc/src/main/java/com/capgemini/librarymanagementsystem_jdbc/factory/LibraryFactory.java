package com.capgemini.librarymanagementsystem_jdbc.factory;

import com.capgemini.librarymanagementsystem_jdbc.dao.AdminDao;
import com.capgemini.librarymanagementsystem_jdbc.dao.AdminDaoImple;
import com.capgemini.librarymanagementsystem_jdbc.dao.AdminUserDao;
import com.capgemini.librarymanagementsystem_jdbc.dao.AdminUserDaoImple;
import com.capgemini.librarymanagementsystem_jdbc.dao.UserDao;
import com.capgemini.librarymanagementsystem_jdbc.dao.UserDaoImple;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminService;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminServiceImple;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminUserService;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminUserServiceImple;
import com.capgemini.librarymanagementsystem_jdbc.service.UserService;
import com.capgemini.librarymanagementsystem_jdbc.service.UserServiceImple;

public class LibraryFactory {
	public static AdminDao getAdminDao() {
		return new AdminDaoImple();
	}
	public static AdminUserDao getAdminUserDao() {
		return new AdminUserDaoImple();
	}
	public static UserDao getUserDao() {
		return new UserDaoImple();
	}

	public static AdminService getAdminService() {
		return new AdminServiceImple();
	}
	public static AdminUserService getAdminUserService() {
		return new AdminUserServiceImple();
	}
	public static UserService getUserService() {
		return new UserServiceImple();
	}

}
