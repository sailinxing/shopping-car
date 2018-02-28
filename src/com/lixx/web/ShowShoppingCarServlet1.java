package com.lixx.web;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.lixinxin.domain.Product;

public class ShowShoppingCarServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");		
		// Cookie[] cookies =request.getCookies();
		// String car = null;
		// if (cookies != null) {
		// for (Cookie c : cookies) {
		// if (c.getName().equals("car")) {
		// car = c.getValue();
		// }
		// }
		// }
		// System.out.println(car);
		// if (car != null) {
		// String[] pids = car.split("-");
		// String sql = "select * from product where pid=?";
		// response.getWriter().write("<html>");
		// response.getWriter().write("<body>");
		// Product product = null;
		// for (String pid : pids) {
		// QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		// try {
		// product = qr.query(sql, new BeanHandler<Product>(Product.class),
		// pid);
		// response.getWriter().write("产品名：" + product.getPname() + " 价格:" +
		// product.getPrice()+"<br/>");
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }
		HttpSession session = request.getSession();
		//持久化
		String id = session.getId();
		Cookie cookie=new Cookie("JSESSIONID", id);
		cookie.setMaxAge(3600);
		response.addCookie(cookie);
		Object obj = session.getAttribute("car");
		if (obj != null) {
			// ArrayList<Product> list = (ArrayList<Product>) obj;
			if (obj instanceof ArrayList<?>) {
				ArrayList<?> list = (ArrayList<?>) obj;
				response.getWriter().write("<html>");
				response.getWriter().write("<body>");
				for (Object object : list) {
					if (object instanceof Product) {
						Product product = (Product) object;
						response.getWriter()
								.write("产品名：" + product.getPname() + "    价格:" + product.getPrice() + "<br/>");
					}
				}
				response.getWriter().write("</html>");
				response.getWriter().write("</body>");
			}
		} else {
			response.getWriter().write("您还未在购物车加入任何东西！<br/>");
		}
		response.getWriter().write("<a href='/day14_practice/'>返回</a><br/>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}