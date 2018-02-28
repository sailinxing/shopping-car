package com.lixx.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.lixinxin.Utils.C3P0Utils;
import com.lixinxin.domain.Product;

public class AddCarServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		//³Ö¾Ã»¯
		String jid = session.getId();
		Cookie cookie=new Cookie("JSESSIONID", jid);
		cookie.setMaxAge(3600);
		response.addCookie(cookie);
		String id = request.getParameter("id");		
		Object obj = session.getAttribute("car");
		Product product = null;
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from product where pid=?";
		try {
			product = qr.query(sql, new BeanHandler<Product>(Product.class), id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (obj == null) {
			ArrayList<Product> list = new ArrayList<>();
			list.add(product);			
			session.setAttribute("car", list);
		}else{				
//				ArrayList<Product> list=(ArrayList<Product>)obj;				
//				list.add(product);	
//				session.setAttribute("car", list);
			if(obj instanceof ArrayList<?>){
				ArrayList<?> list=(ArrayList<?>)obj;
				ArrayList<Product> listPro = new ArrayList<>();
				for (Object object : list) {
					if(object instanceof Product){
						Product pro=(Product)object;
						listPro.add(pro);
					}
				}
				listPro.add(product);
				session.setAttribute("car",listPro);
			}	
				
		}
		// Cookie cookie=new Cookie("car", id);
		// System.out.println(id);
		// Cookie[] cookies = request.getCookies();
		// String newId="";
		// if(cookies!=null){
		// for (Cookie c : cookies) {
		// if(c.getName().equals("car")){
		// String value = c.getValue();
		// newId=value+"-"+id;
		// System.out.println(newId);
		// cookie.setValue(newId);
		// }
		// }
		// }
		// response.addCookie(cookie);
		response.getWriter().write("<a href='/day14_practice/showProductServlet'>·µ»Ø</a><br/>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}