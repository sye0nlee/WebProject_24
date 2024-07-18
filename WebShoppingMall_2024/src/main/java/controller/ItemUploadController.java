package controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.rmi.CORBA.Util;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.dbcp.dbcp2.Utils;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.DBConnPool;
import common.Item.*;
import fileupload.FileUtil;
import fileupload.MyFileDTO;
import oracle.sql.BLOB;



@MultipartConfig(
		
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*10,
		maxRequestSize = 1024*1024*10*10
)
@WebServlet("/ItemUploadController")
public class ItemUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemDAO itemDao;
	private DBConnPool db;
	boolean result1;
	boolean result2;
	
    public ItemUploadController() {
        super();
        itemDao = new ItemDAO();
        db = new DBConnPool();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ItemDTO item = new ItemDTO();

    	
    	

    	
    	try {
    		int maxFileSize = 1024*1024*10;
    		
    		//String originName = file.getSubmittedFileName();
    		String savePath = request.getServletContext().getRealPath("/File");
    		MultipartRequest multi = new MultipartRequest(request, savePath, maxFileSize, "utf-8", new DefaultFileRenamePolicy());
    		//String filePath = savePath + File.separator + 
    		result1 = itemDao.insertImg(item, request, multi);
    		
    		//img.setFile(multi.getFile("file"));
        	item.setItem_name(multi.getParameter("item_name"));
        	item.setItem_seller(multi.getParameter("item_seller"));
        	item.setItem_info(multi.getParameter("item_info"));
        	item.setItem_price(0);
        	item.setItem_left(0);
        	item.setItem_like(0);
        	item.setItem_view(0);
        	item.setInfo_pwd(multi.getParameter("info_pwd"));
        	
    		result1 = itemDao.insertItem(item);
    		
    		
    		response.sendRedirect("index.jsp");
    		
    		
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
    public void alert_no(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("EUC-KR");
		PrintWriter writer  = response.getWriter();
		writer.println("<script>");
		writer.println("alert('등록 오류');");
		writer.println("history.back();");
		writer.println("</script>");
		writer.flush();
		return;
		
		}
    
}
