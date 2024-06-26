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
		psmt.setString(1, bean.getId());
		psmt.setString(2, bean.getPwd());
		psmt.setString(3, bean.getName());
		psmt.setString(4, bean.getEmail());
		psmt.setString(5, bean.getNickname());
		psmt.setInt(6, 1);
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
			psmt.setString(1, bean.id);
			psmt.setString(2, bean.pwd);
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
			psmt.setString(1, bean.id);
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
	
	
