package com.care.root.board.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardRepDTO;

public interface BoardService {

	public void boardAllList(Model model);
	//글작성 추가하기
	public String writeSave(MultipartHttpServletRequest mul, HttpServletRequest request);
	
	//목록에 대한 내용 보기
	public void contentView(int writeNo, Model model);
	
	//수정된 내용을 저장
	public String modify(MultipartHttpServletRequest mul, HttpServletRequest request);
	
	//내용을 삭제하기
	public String boardDelete(int writeNo, String imageFileName, HttpServletRequest request);
	
	//답변글을 추가하기
	public void addReply(BoardRepDTO dto);
	
	//답변글 조회
	public List<BoardRepDTO> getRepList(int write_group);
	
}
