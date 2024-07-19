package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.DBConnPool;
import common.Item.ItemDAO;

@WebServlet("/ItemListController")
public class ItemListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemDAO itemDao;
	private DBConnPool db;
	boolean result;
	
    public ItemListController() {
        super();
        itemDao = new ItemDAO();
        db = new DBConnPool();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * try { itemDao.itemList(); } catch (SQLException e) { e.printStackTrace(); }
		 */
	}

}
