package common.Item;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import common.DBConnPool;
import common.Member.MemberDTO;


public class ItemDAO {
	private DBConnPool db;
	boolean result = false;
	
	public ItemDAO(){
		db = new DBConnPool();
	}
	
	public boolean insertItem(ItemDTO bean) throws SQLException{

		String sql = "INSERT INTO \"Item\" VALUES (Item_seq.NEXTVAL, ?, ?, ?, ?, ?, ?,?,?,?)";
		result = db.insertItem(sql, bean);

		return result;
	}


}