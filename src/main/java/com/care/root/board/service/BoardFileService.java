package com.care.root.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface BoardFileService {
	public static String IMAGE_REPO="c:/spring/image_repo";
	
	//글작성시 사용자에게 메세지 응답
	public String getMessage(HttpServletRequest request, String msg, String url);

	public String saveFile(MultipartFile file);

	public void deleteImage(String originFileName);

	

}
