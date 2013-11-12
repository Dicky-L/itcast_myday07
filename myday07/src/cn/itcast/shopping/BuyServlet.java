package cn.itcast.shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BuyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		Book book = (Book) DB.getMap().get(id);
		
		HttpSession sess = request.getSession();
		List list = (List) sess.getAttribute("list");
		if(list==null){
			list = new ArrayList();
			sess.setAttribute("list", list);
		}
		list.add(book);
		
		//request.getRequestDispatcher("/servlet/ListCartServlet").forward(request, response);
		response.sendRedirect("/myday07/servlet/ListCartServlet");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
