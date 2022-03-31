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
	
	//수정된 내용 저장
		public String modify(HttpServletRequest request) {
			// dto에 사용자의 정보를 넘겨주기
			System.out.println("aaaaaaa");
			System.out.println(request.getParameter("addr"));
			MemberDTO dto= new MemberDTO();
			dto.setId(request.getParameter("id")); //문자열을 정수로 바꾸기
			dto.setAddr(request.getParameter("addr"));
			
			int result=mapper.modify(dto); //mapper에  dto넣기 성공시 1이 반환 , 에러시 0이 반환 try,catch
			//메세지를 전달
			String msg,url;
			if(result ==1 ) {
				//성공시 메세지 만들기
				msg="성공적인 수정";
				url="/member/memberInfo";
			}else {
				msg="수정에 문제";
				url="/member/member_modify?id="+dto.getId();			
			}
			return mfs.getMessage(request, msg, url);
		}
	
	public String memberDelete(String id, HttpServletRequest request) {
		System.out.println("delete aaa");
		int result = mapper.delete(id);
		String msg ,url;
		if(result==1) {
			//삭제되었다면
			msg="성공적인 삭제";
			url="/member/memberInfo";
		}else {
			msg="삭제 실패";
			url="/member/member_modify?id="+id;
		}
		return mfs.getMessage(request, msg, url);
	}
}
