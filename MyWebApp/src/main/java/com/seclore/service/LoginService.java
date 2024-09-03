package com.seclore.service;

public class LoginService {

	public boolean isValidUser(String uname, String pwd) {
		return ("abc".equals(uname) && "123".equals(pwd));
	}

}
