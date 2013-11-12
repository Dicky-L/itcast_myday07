package cn.itcast.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		List<User> list = DB.getAll();
		for(User user : list){
			if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
				request.getSession().setAttribute("user", user);
				response.sendRedirect("/myday07/index.jsp");
				return;
			}			
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("账号或密码错误!");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}

class DB {
	private static List<User> list = new ArrayList();
	static{
		list.add( new User("aaa","123"));
		list.add( new User("bbb","123"));
		list.add( new User("ccc","123"));
		list.add( new User("ddd","123"));
	}

	public static List getAll() {

		return list;
	}
}
