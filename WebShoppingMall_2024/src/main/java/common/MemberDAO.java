package common;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class MemberDAO {

	private DBConnPool db;
	boolean result = false;
	public MemberDAO(){
		db = new DBConnPool();
		
		
	}

	public boolean MemberInsert(MemberDTO bean) throws SQLException {
		
		String sql = "insert into \"UserInfo\" values (?, ?, ?, ?, ?, ?, ?, ?)";
		result = db.insert(sql,bean);
			
		return result;
			
	}
	
	
	public boolean MemberCheck(MemberDTO bean) throws SQLException {

		String sql = "SELECT * FROM \"UserInfo\" WHERE \"user_id\" = ? AND \"user_pwd\" = ?";
		result = db.check(sql, bean);
		
		return result;
		
		
	}
	
	
	public boolean isRegisterd(MemberDTO bean) {
		
		String sql = "SELECT * FROM \"UserInfo\" WHERE \"user_id\" = ?";
		result = db.isRegisterd(sql, bean);

        return result;
		
	}
	

}