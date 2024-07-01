package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.DBConnPool;
import common.Member.*;

@WebServlet("/SignUpController")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDao;
	private DBConnPool db;
	boolean result;
	
    public SignUpController() {
        super();
        memberDao = new MemberDAO();
        db = new DBConnPool();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		MemberDTO member = new MemberDTO();
		
		
		if(memberDao.isRegisterd(member)) {
			alert(request, response);
		}else {
			
			member.setUser_id(request.getParameter("user_id"));
			member.setUser_pwd(request.getParameter("user_pwd"));
			member.setUser_name(request.getParameter("user_name"));
			member.setUser_email(request.getParameter("user_email"));
			member.setUser_nickname(request.getParameter("user_nickname"));
			
			try {
				memberDao.MemberInsert(member);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
    		dispatcher.forward(request, response);
		}
		db.close();
	}
    

		
	public void alert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("EUC-KR");
		PrintWriter writer  = response.getWriter();
		writer.println("<script>");
		writer.println("alert('중복된 ID 입니다.');");
		writer.println("history.back();");
		writer.println("</script>");
		writer.flush();
		return;
		
		}
    
    
    
    
    
    
    
	

}
