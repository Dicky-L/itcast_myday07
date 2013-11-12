package cn.itcast.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieDemo03 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charater");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("你浏览的书籍: <br/>");
	
		String id = request.getParameter("id");
		Book book = (Book) DB.getMap().get(id);
		out.print(book.getId() + "<br/>");
		out.print(book.getName() + "<br/>");
		out.print(book.getAuthor() + "<br/>");
		
		String bookHistroy = makeCookie(book.getId(),request);
		Cookie cookie = new Cookie("bookHistroy",bookHistroy);
		cookie.setMaxAge(120);
		response.addCookie(cookie);		
		
		
	}
	private String makeCookie(String id, HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String bookHistroy = null;
	//	System.out.println("length:  "+cookies.length);
		for(int i = 0; cookies != null && i < cookies.length; i++){
			System.out.println("length_for:  "+cookies.length);
			if(cookies[i].getName().equals("bookHistroy")){
				System.out.println();
				bookHistroy = cookies[i].getValue();
			}
		}
		if(bookHistroy == null){
			bookHistroy = id;
			return bookHistroy;
		}
		String ids[] = bookHistroy.split("_");
		LinkedList<String> idList = new LinkedList(Arrays.asList(ids));
		System.out.println(idList);
		if(idList.contains(id)){
			idList.remove(id);
		}else{
			if(idList.size() == 3){
				idList.removeLast();
			}
		}
		idList.addFirst(id);
		
		StringBuffer sb = new StringBuffer();
		for(String lid : idList){
			sb.append(lid + "_");			
		}
		
		
		return sb.deleteCharAt(sb.length()-1).toString();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
