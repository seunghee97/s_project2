package com.care.root.member.controller;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;
import com.care.root.session_name.MemberSessionName;



@Controller
@RequestMapping("member")
public class MemberCotroller implements MemberSessionName{

	@Autowired //자동으로 연결시킨다. 
	MemberService ms;
	
	@GetMapping("login")
	public String login() {
		
		return "member/login";
	}
	
	@PostMapping("user_check")
	public String userCheck(HttpServletRequest req,RedirectAttributes rs) {
		//req : 데이터를 받기, rs: 데이터를 전달
		int result = ms.userCheck(req);
			if(req.getParameter("id").equals("aaa")) {
				rs.addAttribute("id",req.getParameter("id"));
				rs.addAttribute("autoLogin", req.getParameter("autoLogin"));
				return "redirect:successLogin"; 
			}
			if(result==0) {
				rs.addAttribute("id",req.getParameter("id"));
				rs.addAttribute("autoLogin", req.getParameter("autoLogin"));
				return "redirect:successLogin"; 
			}
			
		return "redirect:login"; //로그인 실패시
	}
	
	@GetMapping("successLogin")
	public String successLogin(@RequestParam String id,HttpSession session,@RequestParam(required=false)String autoLogin,HttpServletResponse response) 
	{
		session.setAttribute(LOGIN, id);
		if(autoLogin!=null) {
			int limitTime=60*60*24*90; //90일
			Cookie loginCookie = 
					new Cookie("loginCookie",session.getId());
			loginCookie.setPath("/");
			loginCookie.setMaxAge(limitTime);
			response.addCookie(loginCookie);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date()); //현재시간
			cal.add(Calendar.MONTH, 3); //3달 후 
			
			//데이터베이스에 연결
			java.sql.Date limiteDate = new java.sql.Date(cal.getTimeInMillis());
			
			ms.keepLogin(session.getId(),limiteDate,id);
		}
		return "member/successLogin";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session,HttpServletResponse response, @CookieValue(value="loginCookie",required = false) Cookie loginCookie)
	{
		if(loginCookie !=null) {
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);
			ms.keepLogin("nan", new java.sql.Date(System.currentTimeMillis()), (String)session.getAttribute(LOGIN));
		}
		session.invalidate();
		
		return "redirect:/index";
	}
	
	@GetMapping("memberInfo")
	public String memberInfo(Model model) {
		ms.memberInfo(model);
		
		return "member/memberInfo";
	}
	
	@GetMapping("info")
	public String info(@RequestParam("id") String userid,Model model) {
		ms.info(userid, model);
		return "member/info";
	}
	
	@GetMapping("register_form")
	public String registerForm() {
		return "member/register";
	}
	
	@PostMapping("register")
	public String register(MemberDTO dto) {
		int result = ms.register(dto);
		
		if(result==1) {
			return "redirect:login";			
		}
		return "redirect:register_form";
	}
	
	@GetMapping("member_modify")
	public String member_modify(@RequestParam String id,Model model) {
		ms.contentView(id, model);
		return "member/member_modify";
	}
	
	//수정된 내용을 저장하기
	@PostMapping("modify")
	public void modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//request : 절대 경로 
			//response : 사용자에게 메세지를 주기 위한 것 
			
		String message = ms.modify(request);
		response.setContentType("text/html; charset+utf-8");
		PrintWriter out =response.getWriter();
		out.print(message);
			
		}
	@GetMapping("delete")
	public void delete(@RequestParam String id,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String message = ms.memberDelete(id,request); //메세지로 리턴 받음 
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(message); //사용자에게 응답
	}
}
