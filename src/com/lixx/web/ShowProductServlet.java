package com.lixx.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lixinxin.Utils.C3P0Utils;
import com.lixinxin.domain.Product;

public class ShowProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product";
		List<Product> products=null;
		try {
			products= qr.query(sql, new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().write("<html>");
		response.getWriter().write("<body>");
		for (Product product : products) {
			String pname = product.getPname();
			int pid = product.getPid();
			response.getWriter().write(pname+"<a href='/day14_practice/addCarServlet?id="+pid+"'>加入购物车</a><br/>");
		}
		response.getWriter().write("<a href='/day14_practice/'>返回</a><br/>");
		response.getWriter().write("</html>");
		response.getWriter().write("</body>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}