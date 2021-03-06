package com.care.root.member.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.service.BoardFileService;
import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberMapper mapper;
	
	@Autowired MemberFileService mfs;
	
	BCryptPasswordEncoder en = new BCryptPasswordEncoder();
	
	public int userCheck(HttpServletRequest req) {
		MemberDTO dto = mapper.getMember(req.getParameter("id"));
		if(dto!=null) {
			if(en.matches(req.getParameter("pw"), dto.getPw())||dto.getPw().equals(req.getParameter("pw"))) {
				return 0;
			}
		}
		return 1; 
	}
	
	public void keepLogin(String sessionId,Date limiteDate,String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		map.put("limitDate", limiteDate);
		map.put("id", id);
		
		mapper.keepLogin(map);
	}
	
	public MemberDTO getUserSession(String sessionId) {
		return mapper.getUserSession(sessionId);
	}
	
	public void memberInfo(Model model) {
		try {
		//ArrayList<MemberDTO> a = mapper.memberInfo();
		//System.out.println(a.size());
		model.addAttribute("memberlist",mapper.memberInfo());
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		}
	
	public void info(String userid,Model model) {
		MemberDTO dto =mapper.getMember(userid);
		model.addAttribute("info",dto);
	}
	
	public int register(MemberDTO dto) {
		String securePw = en.encode(dto.getPw());
		
		dto.setPw(securePw);
		dto.setSessionId("nan");
		try {
			return mapper.register(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void contentView(String id, Model model) {
		model.addAttribute("data", mapper.contentView(id));
	}
	
	//????????? ?????? ??????
		public String modify(HttpServletRequest request) {
			// dto??? ???????????? ????????? ????????????
			System.out.println("aaaaaaa");
			System.out.println(request.getParameter("addr"));
			MemberDTO dto= new MemberDTO();
			dto.setId(request.getParameter("id")); //???????????? ????????? ?????????
			dto.setAddr(request.getParameter("addr"));
			
			int result=mapper.modify(dto); //mapper???  dto?????? ????????? 1??? ?????? , ????????? 0??? ?????? try,catch
			//???????????? ??????
			String msg,url;
			if(result ==1 ) {
				//????????? ????????? ?????????
				msg="???????????? ??????";
				url="/member/memberInfo";
			}else {
				msg="????????? ??????";
				url="/member/member_modify?id="+dto.getId();			
			}
			return mfs.getMessage(request, msg, url);
		}
	
	public String memberDelete(String id, HttpServletRequest request) {
		System.out.println("delete aaa");
		int result = mapper.delete(id);
		String msg ,url;
		if(result==1) {
			//??????????????????
			msg="???????????? ??????";
			url="/member/memberInfo";
		}else {
			msg="?????? ??????";
			url="/member/member_modify?id="+id;
		}
		return mfs.getMessage(request, msg, url);
	}
}
