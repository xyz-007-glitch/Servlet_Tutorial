package com.seclore.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CaptchaServlet
 */
@WebServlet("/captcha.jpg")
public class CaptchaServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		
		String captcha = "";
		for(int i = 0; i < 5; i++) {
			int rno = (int) (Math.random() * str.length());
			captcha += str.charAt(rno);
		}
		
		response.setContentType("image/jpeg"); // HTTP header
		ServletOutputStream out = response.getOutputStream();
		
		BufferedImage img = new BufferedImage(150, 50, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, 150, 50);
		g.setColor(Color.RED);
		g.setFont(new Font("Harrington", Font.BOLD, 32));
		g.drawString(captcha, 29, 37);
		
		HttpSession session = request.getSession();
		session.setAttribute("captcha", captcha);
		
		ImageIO.write(img, "jpeg", out);
		
		
	}

}
