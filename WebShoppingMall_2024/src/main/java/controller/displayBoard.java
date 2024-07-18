package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.DBConnPool;
import common.Item.ItemDAO;
import common.Item.ItemDTO;

@WebServlet("/displayBoard")
public class displayBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemDAO itemDao;
	private DBConnPool db;
	
    public displayBoard() {
        super();
        itemDao = new ItemDAO();
        db = new DBConnPool();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDTO item = new ItemDTO();
		try {
    		
  
    		//itemDao.displayItem(item);
    		
    		
    		response.sendRedirect("index.jsp");
    		
    		
		} catch (Exception e) {
    		
    		e.printStackTrace();
    		
		}
		
		db.close();
	}

}
