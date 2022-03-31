package com.care.root.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;
import com.care.root.session_name.MemberSessionName;

public class AutoLoginInterceptor  extends HandlerInterceptorAdapter
									implements MemberSessionName{
	@Autowired
	MemberService ms;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); //쿠키 가져오기
		//Cookie[] ck =request.getCookies();
		
		if(loginCookie != null) {
			MemberDTO dto = ms.getUserSession(loginCookie.getValue());
			
			if(dto != null) {
				HttpSession session = request.getSession();
				session.setAttribute(LOGIN, dto.getId());
			}
		}
	
		System.out.println("====test===");
		
		return true;
	}

}
