package com.seclore.servlet;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seclore.service.DatabaseLoginService;
import com.seclore.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String captcha = request.getParameter("captcha");

		DatabaseLoginService loginService = new DatabaseLoginService();

		HttpSession session = request.getSession();
		String orignalCaptcha = (String) session.getAttribute("captcha");

		System.out.println(orignalCaptcha + " " + captcha);

		if (!orignalCaptcha.equals(captcha)) {
			response.sendRedirect("login.html?error=2");
		} else {
			boolean isValid = loginService.isValidUser(uname, pwd);

			if (isValid) {
				String remember = request.getParameter("remember");

				if ("y".equals(remember)) {
//				Cookie c1 = new Cookie("uname", uname);
//				c1.setMaxAge(60 * 60); //1hr
//				Cookie c2 = new Cookie("pwd", pwd);
//				c2.setMaxAge(60 * 60);
//				response.addCookie(c1);
//				response.addCookie(c2);
					String data = uname + ":" + pwd;
					// Replace encoder with some encryption
					String encodedData = Base64.getEncoder().encodeToString(data.getBytes());
					Cookie c = new Cookie("loginData", encodedData);
					c.setMaxAge(60 * 60);
					response.addCookie(c);
				}
				response.sendRedirect("welcome.html");
			} else {
				response.sendRedirect("login.html?error=1");
			}
		}
	}

}
