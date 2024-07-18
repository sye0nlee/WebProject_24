package fileupload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileUtil{
public static String uploadFile(HttpServletRequest req, String sDirectory) throws ServletException, IOException{
		
		Part part = req.getPart("file");
		String partHeader = part.getHeader("content-disposition");
		String[] phArr = partHeader.split("filename");
		String originalFileName = phArr[1].trim().replace("\"", "");
		if(!originalFileName.isEmpty()) {
			part.write(sDirectory+ File.separator + originalFileName);
		}
		return originalFileName;
		
	}
	
	
}