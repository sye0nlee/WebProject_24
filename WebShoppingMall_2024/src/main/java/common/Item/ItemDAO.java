package common.Item;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;

import common.DBConnPool;
import common.Member.MemberDTO;
import fileupload.MyFileDAO;
import fileupload.MyFileDTO;


public class ItemDAO {
	private DBConnPool db;
	boolean result = false;
	ResultSet rs;
	public ItemDAO(){
		db = new DBConnPool();
	}
	
	public boolean insertItem(ItemDTO bean) throws SQLException{
		String sql = "INSERT INTO \"Item\" VALUES (Item_seq.NEXTVAL-1, ?, ?, ?, ?, ?,?,?,?)";
		result = db.insertItem(sql, bean);
		return result;
	}

	public boolean insertImg(ItemDTO bean, HttpServletRequest request, MultipartRequest multi) throws Exception{
		String sql = "INSERT INTO \"ItemImg\" VALUES (Img_seq.NEXTVAL, Item_seq.NEXTVAL, ?, ?)";
		result = db.InsertImg(sql, bean, request, multi);
		return result;

	}

	public ArrayList<ItemDTO> displayItem(int pageNumber) throws SQLException, IOException{
		String sql = "SELECT * FROM \"Item\" ORDER BY \"item_id\" DESC";
		return db.displayItem(sql, pageNumber);
	

	}
	public String displayImg(String id) throws SQLException, IOException{
		String sql = "SELECT img_name from \"ItemImg\" where \"item_id\" = ?";
		return db.displayImg(sql,id);
	

	}
	
	

}