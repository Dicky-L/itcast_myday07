package cn.itcast.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieDemo01 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("本网站有以下书籍: <br/> ");
		Map<String, Book> map = DB.getMap();
		for(Map.Entry<String, Book> entry : map.entrySet()){
			Book book = entry.getValue();
			out.print("<a href='/myday07/servlet/CookieDemo03?id="+ entry.getKey() +"'>"+ book.getName() +"<a/><br/>");
		}
		//request.getRequestDispatcher("").forward(request, response);
		
		out.print("你曾经浏览过一下书籍: <br/>");
		Cookie cookie = null;
		Cookie cookies[] = request.getCookies();
		for(int i = 0; cookies!=null && i < cookies.length; i++){
			System.out.println("sdfesfesfsefsef" + cookies[0]);
			if(cookies[i].getName().equals("bookHistroy")){
				System.out.println("sdfesfesfsefsef2" + cookies[i]);
				cookie = cookies[i];
			}
		} 
		if(cookie != null){			
			String bookHistroy = cookie.getValue();
			String ids[] = bookHistroy.split("\\_");
			for(String id : ids){
				Book book = (Book) DB.getMap().get(id);
				
				out.print(book.getName() + "<br/>");
			}
		}		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

class DB{
	private static Map<String,Book> map = new HashMap();
	static{
		map.put("1", new Book("1","Javaweb 开发","XXXX"));
		map.put("2", new Book("2","jdbc 开发","X X"));
		map.put("3", new Book("3","struts2 开发","XXX X"));
		map.put("4", new Book("4","spring 开发","XX"));
		map.put("5", new Book("5","hibernate 开发","XX XX"));

	}
	public static Map getMap(){
		return map;
	}
}

class Book{
	private String id;
	private String name;
	private String author;
	
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String id, String name, String author) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	
}