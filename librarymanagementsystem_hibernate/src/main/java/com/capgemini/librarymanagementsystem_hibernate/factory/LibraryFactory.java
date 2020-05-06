package com.capgemini.librarymanagementsystem_hibernate.factory;

import com.capgemini.librarymanagementsystem_hibernate.dao.LMSUsersDAO;
import com.capgemini.librarymanagementsystem_hibernate.dao.LMSUsersDAOImplement;
import com.capgemini.librarymanagementsystem_hibernate.service.LMSUsersService;
import com.capgemini.librarymanagementsystem_hibernate.service.LMSUsersServiceImplement;

public class LibraryFactory {
	public static LMSUsersDAO getUsersDao() {
		return new LMSUsersDAOImplement();
	}

	public static LMSUsersService getUsersService() {
		return new LMSUsersServiceImplement();
	}

}
