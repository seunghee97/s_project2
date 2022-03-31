package com.care.root.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.root.board.dto.BoardRepDTO;
import com.care.root.board.service.BoardService;
import com.care.root.session_name.MemberSessionName;

@Controller
@RequestMapping("board")
public class BoardRepController implements MemberSessionName{
	@Autowired BoardService bs;
	
		//답변글 추가하기
		@PostMapping(value = "addReply",produces = "application/json;charset=utf-8")
		public void addReply(@RequestBody Map<String, Object> map, HttpSession session) {
			BoardRepDTO dto =new BoardRepDTO();
			dto.setId((String)session.getAttribute(LOGIN)) ;
			dto.setWrite_group(Integer.parseInt((String)map.get("write_no")));
			dto.setTitle((String)map.get("title"));
			dto.setContent((String)map.get("content"));
			
			bs.addReply(dto);
			
		}
	
		//답변글 조회하기
		@GetMapping(value = "replyData/{write_group}",produces = "application/json;charset=utf-8")
		@ResponseBody
		public List<BoardRepDTO> replyData(@PathVariable int write_group){
			return bs.getRepList(write_group);
		}
}
