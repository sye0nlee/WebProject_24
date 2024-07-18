package common;


import java.io.IOException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.http.HttpServletRequest;

import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;


import common.Item.ItemDTO;
import common.Member.MemberDTO;

public class DBConnPool{
	public Connection conn;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
	
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
	
	public boolean insertMember(String sql, MemberDTO bean) throws SQLException {
		
		PreparedStatement psmt = conn.prepareStatement(sql);
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
			PreparedStatement psmt = conn.prepareStatement(sql);
			
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
			psmt.setString(1, bean.getUser_id());
			rs = psmt.executeQuery();
		
			if (rs.next()) {
				result = true;
			 }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
		
	}
	
	

	public boolean insertItem(String sql, ItemDTO bean) throws SQLException{
		boolean result = false;
		
		try {
			
			//PreparedStatement psmt = conn.prepareCall("{call insert_item(?,?,?,?,?,?,?,?)}");
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1,  bean.getItem_name());
			psmt.setInt(2,  bean.getItem_price());
			psmt.setInt(3,  bean.getItem_left());
			psmt.setInt(4,  bean.getItem_like());
			psmt.setInt(5,  bean.getItem_view());
			psmt.setString(6,  bean.getItem_seller());
			psmt.setString(7,  bean.getItem_info());
			psmt.setString(8,  bean.getInfo_pwd());
			//psmt.execute();
			rs = psmt.executeQuery();
			
			
			
			 
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
		
		
	 
	}
	
	


    public boolean InsertImg(String sql, ItemDTO bean, HttpServletRequest request, MultipartRequest multi) throws IOException {
    	boolean result = false;

        try {
        	PreparedStatement psmt = conn.prepareStatement(sql);

            //psmt.setString(1, bean.getItem_id());
        	String savePath = request.getServletContext().getRealPath("/File");
        	 
        	// 전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
        	String fileName = multi.getFilesystemName("fileName");
        	 
        	// 업로드한 파일의 전체 경로를 DB에 저장하기 위함
        	String m_fileFullPath = savePath + "\\" + fileName;
        	 
            psmt.setString(1, m_fileFullPath);
            psmt.executeUpdate();

            System.out.println("Image saved to database successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
	
    public void pagingBoard(String sql) {
    	int totalRowCount = 0;
    	try {
    		
			PreparedStatement psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				totalRowCount = rs.getInt(1);
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public ArrayList<ItemDTO> displayItem(String sql, int pageNumber) throws IOException {
    	
    	
    	ArrayList<ItemDTO> ItemList = new ArrayList<ItemDTO>();
    	
    	String pageNo = "1";
    	
    	int tmpPageNo = Integer.parseInt(pageNo);
    	int beginRow = (tmpPageNo - 1) * 10;
    	int row = 10;
    	
    	
    	try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			/*
			 * psmt.setInt(1, beginRow); psmt.setInt(2, row);
			 */
			rs = psmt.executeQuery();
			while(rs.next()) {
				ItemDTO item = new ItemDTO();
				item.setItem_name(rs.getString(2));
				item.setItem_price(rs.getInt(3));
				ItemList.add(item);
				//map.put("item_name", rs.getString("item_name"));
				//map.put("item_price", rs.getString("item_price"));
				//list.add(map);
				
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return ItemList;
    	


	
	
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
	
	
