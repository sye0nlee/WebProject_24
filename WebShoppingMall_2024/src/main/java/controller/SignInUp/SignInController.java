package controller.SignInUp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.DBConnPool;
import common.Member.MemberDAO;
import common.Member.MemberDTO;
@WebServlet("/SignInController")
public class SignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDao;
	private DBConnPool db;
	boolean result;

    public SignInController() {
        super();
        memberDao = new MemberDAO();
        db = new DBConnPool();
    }


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	request.setCharacterEncoding("UTF-8");

    	makeCookie(request, response, request.getParameter("user_id"));

		MemberDTO member = new MemberDTO();
		member.setUser_id(request.getParameter("user_id"));
		member.setUser_pwd(request.getParameter("user_pwd"));

		try {
			result = memberDao.MemberCheck(member);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(result) {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
    		dispatcher.forward(request, response);
    	}else {
    		alert(request, response);
    	}
		db.close();
	}

	public void makeCookie(HttpServletRequest request, HttpServletResponse response, String id) {
		Cookie cookie = new Cookie("userId", id);
		String cbx = request.getParameter("loginChk");

		if(cbx != null) {
			cookie.setMaxAge(60*2);
			response.addCookie(cookie);				//쿠키 유효 시간(초)
			cookie.setPath("/");					//최상위 루트 경로로 옮기기. 경로에 상관 없이 항상 쿠키를 받는다.
		}else {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

	}


	public void alert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("EUC-KR");
		PrintWriter writer  = response.getWriter();
		writer.println("<script>");
		writer.println("alert('ID/PWD 값을 확인해주세요.');");
		writer.println("history.back();");
		writer.println("</script>");
		writer.flush();
		return;

		}
	}
