package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool{
	public Connection conn;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	
	public DBConnPool(){
		
		try{
			
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");
			
			conn = source.getConnection();
			
			System.out.println("DB커넥션 풀 연결 성공");
			
			
		}catch(Exception e){
			System.out.println("DB커넥션 풀 연결 실패");
			//e.printStackTrace();
		}
	}
	
	public boolean insert(String sql, MemberDTO bean) throws SQLException {
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, bean.getUser_id());
		psmt.setString(2, bean.getUser_pwd());
		psmt.setString(3, bean.getUser_name());
		psmt.setString(4, bean.getUser_gender());
		psmt.setString(5, null);
		psmt.setInt(6, 1);
		psmt.setString(7, bean.getUser_email());
		psmt.setString(8, bean.getUser_nickname());
		rs = psmt.executeQuery();
		rs.close();
		psmt.close();
		conn.close();
		return true;
	}
	
	public boolean check(String sql, MemberDTO bean) throws SQLException {
		
		boolean result = false;
		try { 
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, bean.getUser_id());
			psmt.setString(2, bean.getUser_pwd());
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				result = true;		
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	public boolean isRegisterd(String sql, MemberDTO bean) {
		boolean result = false;
		
		try { 	//중복 O
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, bean.user_id);
			rs = psmt.executeQuery();
		
			if (rs.next()) {
				result = true;
			 }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
		
	}
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
			
			System.out.println("DB커넥션 풀 자원 반납");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
	
	
