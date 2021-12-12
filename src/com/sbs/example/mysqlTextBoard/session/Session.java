package com.sbs.example.mysqlTextBoard.session;

public class Session {

	private  int loginedMemberId;
	private String currentBoardCode;
	
	public Session() {
		//공지사항을 기본 선택된 게시판으로 지정
		currentBoardCode = "notice";
	}
	
	public  void logOut() {
		loginedMemberId = 0;
	}

	public  void logIn(int id) {
		loginedMemberId = id;
	}

	public boolean isLogined() {
		return loginedMemberId > 0;
	}

	public int getLoginedMemberId() {
		return loginedMemberId;
	}

	public String getCurrentBoardCode() {
		return currentBoardCode;
	}

	public void setCurrentBoardCode(String currentBoardCode) {
		this.currentBoardCode = currentBoardCode;
	}
	
}
