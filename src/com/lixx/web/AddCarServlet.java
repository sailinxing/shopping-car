package com.lixx.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		Cookie cookie=new Cookie("car", id);
//		System.out.println(id);
		Cookie[] cookies = request.getCookies();
		String newId="";
		if(cookies!=null){
			for (Cookie c : cookies) {
				if(c.getName().equals("car")){
					String value = c.getValue();
					newId=value+"-"+id;
//					System.out.println(newId);
					cookie.setValue(newId);
				}
			}
		}		
		response.addCookie(cookie);
		response.getWriter().write("<a href='/day14_practice/showProductServlet'>·µ»Ø</a><br/>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}