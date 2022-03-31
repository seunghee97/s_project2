package com.care.root.member.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.member.dto.MemberDTO;

public interface MemberService {
	
	public int userCheck(HttpServletRequest req);
	
	public void keepLogin(String sessionId,Date limiteDate,String id);
	
	public MemberDTO getUserSession(String sessionId);
	
	public void memberInfo(Model model);
	
	public void info(String userid,Model model);

	public int register(MemberDTO dto);

	//회원정보 수정
	public void contentView(String id, Model model);

	//수정된 내용을 저장
	public String modify(HttpServletRequest request);

	public String memberDelete(String id, HttpServletRequest request);
	
	
}
