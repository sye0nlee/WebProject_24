package fileupload;

import common.DBConnPool;
import common.Item.ItemDTO;

public class MyFileDAO extends DBConnPool{
	
	public int insertFile(MyFileDTO dto, ItemDTO item_dto) {
		int applyResult = 0;
		
		try {
			String sql = "INSERT INTO \"ItemImg\" VALUES (Img_seq.NEXTVAL, ?, ?)";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, item_dto.getItem_id());
			psmt.setString(2, dto.getFile());
			applyResult = psmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return applyResult;
	}
}