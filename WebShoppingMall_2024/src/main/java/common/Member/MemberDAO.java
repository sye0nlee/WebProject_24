package common.Member;

import java.sql.SQLException;

import common.DBConnPool;


public class MemberDAO {
	private DBConnPool db;
	boolean result = false;
	
	public MemberDAO(){
		db = new DBConnPool();
	}

	public boolean MemberInsert(MemberDTO bean) throws SQLException {
		String sql = "insert into \"UserInfo\" values (?, ?, ?, ?, ?, ?, ?, ?)";
		result = db.insertMember(sql,bean);

		return result;

	}

	public boolean MemberCheck(MemberDTO bean) throws SQLException {
		String sql = "SELECT * FROM \"UserInfo\" WHERE \"user_id\" = ? AND \"user_pwd\" = ?";
		result = db.loginCheck(sql, bean);

		return result;

	}

	public boolean isRegisterd(MemberDTO bean) {
		String sql = "SELECT * FROM \"UserInfo\" WHERE \"user_id\" = ?";
		result = db.isRegisterd(sql, bean);

        return result;

	}


}