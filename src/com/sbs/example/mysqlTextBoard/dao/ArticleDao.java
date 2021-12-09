package com.sbs.example.mysqlTextBoard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sbs.example.mysqlTextBoard.dto.Article;

public class ArticleDao {

	public List<Article> getArticles() {
		List<Article> articles = new ArrayList<Article>();

		String dbmsJdbcUrl = "jdbc:mysql://127.0.0.1:3306/textBoard?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
		String dbmsLoginId = "sbsst";
		String dbmsLoginPw = "1234";

		// 기사 등록
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		// 연결 생성
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbmsJdbcUrl, dbmsLoginId, dbmsLoginPw);
		} catch (SQLException e) {
			e.printStackTrace();
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
