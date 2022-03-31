package com.care.root.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.service.BoardFileService;
import com.care.root.board.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired BoardService bs;
	
	@GetMapping("boardAllList")
	public String boardAllList(Model model) {
		bs.boardAllList(model);
		return "board/boardAllList";
	}
	
	@GetMapping("writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	
	@PostMapping("writeSave")
	public void writeSave(MultipartHttpServletRequest mul,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//사용자에게 글 저장했다고 메세지를 주기
		//저장할 데이터를 db에 추가
		String message=bs.writeSave(mul,request); 
		PrintWriter out = response.getWriter();
		//메세지 유형
		response.setContentType("text/html;charset=utf-8");
		out.print(message);
	}
	
	//글에 대한 내용을 보는 페이지
	@GetMapping("contentView")
	public String contentView(@RequestParam int writeNo,Model model) {
		bs.contentView(writeNo,model);
		return "board/contentView";
	}
	
	//내용 보기 속 이미지를 보이게 하기
	@GetMapping("download")
	public void download(@RequestParam String imageFileName,HttpServletResponse response) throws Exception{
		//응답 방식
		response.addHeader("Content-disposition", "attachment;fileName="+imageFileName);
				
		//파일 경로
		File file = new File(BoardFileService.IMAGE_REPO+"/"+imageFileName);
				
		//이미지 불러오기
		FileInputStream in = new FileInputStream(file);
				
		//사용자에게 보여주기
		FileCopyUtils.copy(in, response.getOutputStream());
		in.close();
	}
	
	@GetMapping("modify_form")
	public String modify_form(@RequestParam int writeNo,Model model) {
		bs.contentView(writeNo, model); //model에 담김 
		return "board/modify_form";
	}
	
	//수정된 내용을 저장하기
	@PostMapping("modify")
	public void modify(MultipartHttpServletRequest mul, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request : 절대 경로 
		//response : 사용자에게 메세지를 주기 위한 것 
		
		String message = bs.modify(mul,request);
		response.setContentType("text/html; charset+utf-8");
		PrintWriter out =response.getWriter();
		out.print(message);
		
	}
	
	@GetMapping("delete")
	public void delete(@RequestParam int writeNo,@RequestParam String imageFileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String message = bs.boardDelete(writeNo,imageFileName,request); //메세지로 리턴 받음 
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(message); //사용자에게 응답
	}
}
