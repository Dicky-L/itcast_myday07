package cn.itcast.form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FormSubmitServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter write = response.getWriter();	
		
		boolean b = isToken(request);		
		if(!b){
			write.print("重复提交!");
			return;
		}
		
		String username = request.getParameter("username");
		String token_cline = request.getParameter("token");	
		System.out.println(username +"  :　　"+ token_cline);
		write.print(username +"  :　　"+ token_cline);
		
		request.getSession().removeAttribute("token");
		

		
		
	}
	private boolean isToken(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String token_cline = request.getParameter("token");				
		if(token_cline == null){
			return false;
		}
		String token_server = (String) session.getAttribute("token");
		if(token_server==null){
			return false;
		}
		
		if(!token_cline.equals(token_server)){
			return false;
		}
		
		return true;
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
		
	}

}
