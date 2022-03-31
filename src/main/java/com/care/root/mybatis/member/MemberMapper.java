package com.care.root.mybatis.member;

import java.util.ArrayList;
import java.util.Map;

import com.care.root.member.dto.MemberDTO;

public interface MemberMapper {

	public MemberDTO getMember(String id);
	
	public void keepLogin(Map<String, Object>map);
	
	public MemberDTO getUserSession(String sessionId);
	
	public ArrayList<MemberDTO> memberInfo();

	public int register(MemberDTO dto);
	//수정목록보기
	public MemberDTO contentView(String id);
	
	//수정저장
	public int modify(MemberDTO dto);

	public int delete(String id);
	
}
