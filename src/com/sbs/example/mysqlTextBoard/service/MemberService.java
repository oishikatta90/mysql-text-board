package com.sbs.example.mysqlTextBoard.service;

import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.dao.MemberDao;
import com.sbs.example.mysqlTextBoard.dto.Member;

public class MemberService {
	MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.add(loginId, loginPw, name);
	}
	
	//memberId를 가지고 그 아이디를 가진 회원의 멤버의 이름을 돌려주는 메소드
	public Member getMemberById(int memberId) {
		
		return memberDao.getMemberById(memberId);
	}
	
	//입력 받은 아이디랑 등록되어 있는 아이디를 비교해서 있으면 아이디를 가져온다.
	public Member getMemberByLoginId(String loginId) {
		
		return memberDao.getMemberByLoginId(loginId);
	}

}
