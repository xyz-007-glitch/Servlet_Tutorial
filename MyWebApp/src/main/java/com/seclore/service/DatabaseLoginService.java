package com.seclore.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseLoginService extends LoginService {

	public static Connection getConnection() {
		try {
			InputStream is = DatabaseLoginService.class.getResourceAsStream("db.properties");
			Properties p = new Properties();
			p.load(is);
			Class.forName(p.getProperty("driver")); // (Reflection API) Loading the driver in the memory
			return DriverManager.getConnection(p.getProperty("url"), p.getProperty("user"), p.getProperty("pass"));
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
			return null; // throw some user defined exception instead

		}
	}

	public boolean isValidUser(String uname, String pwd) {
		try(Connection conn = getConnection();) {

			// pre compiled sql statement
			// Keep it outside loop
			PreparedStatement stmt = conn.prepareStatement("select * from app_user where uname = ? and password = ?");
			stmt.setString(1, uname);
			stmt.setString(2, pwd);
			

			ResultSet rs = stmt.executeQuery(); // Collection
			while (rs.next()) {
				String username = rs.getString("uname");
				String password = rs.getString("password");
				boolean locked = rs.getBoolean("locked");
				
				if(username.equals(uname) && password.equals(pwd)) {
					return !locked;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
