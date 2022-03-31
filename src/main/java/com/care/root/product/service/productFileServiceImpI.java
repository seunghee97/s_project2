package com.care.root.product.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class productFileServiceImpI implements productFileService{

	public String getMessage(HttpServletRequest request, String msg, String url) {
		String message = null;
		String path=request.getContextPath();
		message="<script>alert('"+msg+"');";
		message+="location.href='"+path+url+"';</script>";
		
		
		return message;
	}
	
	public String saveFile(MultipartFile file) {
		SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd HHmmss-");
		Calendar cal = Calendar.getInstance(); //현재시간 
		String sysFileName = fo.format(cal.getTime());
		sysFileName += file.getOriginalFilename(); //현재시간에 파일이름 붙이기
		
		
		File saveFile = new File(IMAGE_REPO+"/"+sysFileName);
		
		try {
			file.transferTo(saveFile); //파일저장 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sysFileName; //파일 이름  리턴 
	}
	
	public void deleteImage(String originFileName) {
		File file = new File(IMAGE_REPO+"/"+originFileName);
		file.delete();
	}
}
