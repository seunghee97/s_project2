package com.care.root.member.service;

import javax.servlet.http.HttpServletRequest;

public interface MemberFileService {
	
	public String getMessage(HttpServletRequest request, String msg, String url);
}
