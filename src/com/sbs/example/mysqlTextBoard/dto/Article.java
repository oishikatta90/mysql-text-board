package com.sbs.example.mysqlTextBoard.dto;

public class Article {
	public int id;
	public String regDate;
	public String updateDate;
	public String title;
	public String body;
	public String memberId;
	public String boardId;
	
	public Article() {
		super();
	}

	public Article(int id, String regDate, String updateDate, String title, String body, String memberId,
			String boardId) {
		super();
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
		this.memberId = memberId;
		this.boardId = boardId;
	}



	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", title=" + title
				+ ", body=" + body + ", memberId=" + memberId + ", boardId=" + boardId + "]";
	}
	
	
}
