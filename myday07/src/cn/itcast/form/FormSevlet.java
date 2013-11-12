package cn.itcast.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.misc.BASE64Encoder;

public class FormSevlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String token = TokenProcessor.getInstance().generateToken();
		
		HttpSession session = request.getSession();
		session.setAttribute("token", token);
		
		out.print("<form action='/myday07/servlet/FormSubmitServlet' method='post'>");
		out.print("<input type='hidden' name='token' value='"+token+"'>");
		out.print("<input type='text' name='username'>");
		out.print("<input type='submit' value='提交'>");
		out.print("</form>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);

	}

}

class TokenProcessor{
	private TokenProcessor(){};
	private static final TokenProcessor instance = new TokenProcessor();
	
	public static TokenProcessor getInstance(){
		return instance;
	}
	
	public String generateToken(){
		String token = System.currentTimeMillis() + "" + new Random(999999);
		
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] md5 = md.digest(token.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(md5);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}		
	}
	
}