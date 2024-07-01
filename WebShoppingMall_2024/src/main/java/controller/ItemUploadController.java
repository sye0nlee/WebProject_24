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
import common.Item.*;


@WebServlet("/ItemUploadController")
public class ItemUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemDAO itemDao;
	private DBConnPool db;
	boolean result;
	
    public ItemUploadController() {
        super();
        itemDao = new ItemDAO();
        db = new DBConnPool();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	ItemDTO item = new ItemDTO();

    	item.setItem_name(request.getParameter("item_name"));
    	item.setItem_seller(request.getParameter("item_seller"));
    	item.setItem_info(request.getParameter("item_info"));
    	item.setItem_price(Integer.parseInt(request.getParameter("item_price")));
    	item.setItem_left(Integer.parseInt(request.getParameter("item_left")));
    	item.setItem_like(0);
    	item.setItem_view(0);
    	item.setInfo_pwd(request.getParameter("info_pwd"));
    	item.setImg_id("none");
    	try {
    		
    		itemDao.insertItem(item);
    		alert(request, response);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
    		dispatcher.forward(request, response);
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	
  
		db.close();
    	
  
    }
    
    public void alert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("EUC-KR");
		PrintWriter writer  = response.getWriter();
		writer.println("<script>");
		writer.println("alert('등록 완료되었습니다');");
		writer.println("</script>");
		writer.flush();
		return;
		
		}
    
}
