package com.sbs.example.mysqlTextBoard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sbs.example.mysqlTextBoard.dto.Article;

public class ArticleDao {

	public List<Article> getArticles() {
		// 연결 생성
		Connection conn = null;
		List<Article> articles = new ArrayList<Article>();
		try {
			String dbmsJdbcUrl = "jdbc:mysql://127.0.0.1:3306/textBoard?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull&connectTimeout=60000";
			String dbmsLoginId = "sbsst";
			String dbmsLoginPw = "1234";

			// 기사 등록
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}

			try {
				conn = DriverManager.getConnection(dbmsJdbcUrl, dbmsLoginId, dbmsLoginPw);
			} catch (SQLException e) {
				e.printStackTrace();
			}

//			String sql = "UPDATE article";
//			sql += " SET updateDate = NOW()";
//			sql += " WHERE id = 3";

			String sql = "SELECT * FROM article ORDER BY id DESC";

			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				// update면 밑에
				// pstmt.execute();
				
				// select면 밑에
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					int id = rs.getInt("id");
					String regDate = rs.getString("regDate");
					String updateDate = rs.getString("updateDate");
					String title = rs.getString("title");
					String body = rs.getString("body");
					String memberId = rs.getString("memberId");
					String boardId = rs.getString("boardId");
//				System.out.println("아이디 : " + id + "\n만든 시각  : " + regDate + "\n업데이트 된 시각 : " + updateDate + "\n타이틀 : " + title
//						+ "\n내용 : " + body + "\n멤버 아이디 : " + memberId + "\n보드 아이디 : " + boardId);
					Article article = new Article();
					article.id = id;
					article.regDate = regDate;
					article.updateDate = updateDate;
					article.title = title;
					article.body = body;
					article.memberId = memberId;
					article.boardId = boardId;
					
					articles.add(article);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return articles;
	}

	private List<Article> getFakeArticles() {
		List<Article> articles = new ArrayList<Article>();

		Article article;

		// 첫번쨰 가짜 게시물 만들기
		article = new Article();
		article.id = 1;
		article.regDate = "2020-11-22 12:11:10";
		article.updateDate = "2020-11-22 11:11:11";
		article.title = "title";
		article.body = "body";
		article.memberId = "1";
		article.boardId = "1";

		articles.add(article);
		// 두번쨰 가짜 게시물 만들기
		article = new Article();
		article.id = 1;
		article.regDate = "2020-11-22 12:13:10";
		article.updateDate = "2020-11-22 13:11:11";
		article.title = "title2";
		article.body = "body2";
		article.memberId = "1";
		article.boardId = "1";

		articles.add(article);

		return articles;

	}

}
