package com.capgemini.librarymanagementsystem_hibernate.factory;

import com.capgemini.librarymanagementsystem_hibernate.dao.AdminDao;
import com.capgemini.librarymanagementsystem_hibernate.dao.AdminDaoImple;
import com.capgemini.librarymanagementsystem_hibernate.dao.AdminUserDao;
import com.capgemini.librarymanagementsystem_hibernate.dao.AdminUserDaoImple;
import com.capgemini.librarymanagementsystem_hibernate.dao.UserDao;
import com.capgemini.librarymanagementsystem_hibernate.dao.UserDaoImple;
import com.capgemini.librarymanagementsystem_hibernate.service.AdminService;
import com.capgemini.librarymanagementsystem_hibernate.service.AdminServiceImple;
import com.capgemini.librarymanagementsystem_hibernate.service.AdminUserService;
import com.capgemini.librarymanagementsystem_hibernate.service.AdminUserServiceImple;
import com.capgemini.librarymanagementsystem_hibernate.service.UserService;
import com.capgemini.librarymanagementsystem_hibernate.service.UserServiceImple;

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
