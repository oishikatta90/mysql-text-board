package com.sbs.example.mysqlTextBoard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlTextBoard.mysqlutil.SecSql;

public class ArticleDao {

	public List<Article> getArticles() {
		List<Article> articles = new ArrayList<Article>();
		
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC");
		
		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);
		
		for (Map<String, Object> articleMap : articleMapList) {
			articles.add(new Article(articleMap));
		}
		return articles;
	}

	public Article getArticle(int inputedId) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", inputedId);
		
		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);
		
		try {
		if (articleMap.isEmpty()) {
			return null;
		}
		}catch(Exception e) {
			System.out.println("해당 게시물 번호는 없습니다.\n 프로그램을 종료합니다.");
			System.exit(0);
		
		}
		
		return new Article(articleMap);
	}

	public int delete(int inputedId) {
		int affectedRows = 0;
		// 연결 생성
		Connection conn = null;
		try {
			String dbmsJdbcUrl = "jdbc:mysql://127.0.0.1:3306/textBoard?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull&connectTimeout=60000";
			String dbmsLoginId = "sbsst";
			String dbmsLoginPw = "1234";

			// 기사 등록
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
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

			String sql = "DELETE FROM article WHERE id = ?";

			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, inputedId);
				affectedRows = pstmt.executeUpdate();
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
		return affectedRows;
	}

	public int write(String title, String body, int memberId, int boardId) {
		int id = 0;
		// 연결 생성
		Connection conn = null;
		try {
			String dbmsJdbcUrl = "jdbc:mysql://127.0.0.1:3306/textBoard?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull&connectTimeout=60000";
			String dbmsLoginId = "sbsst";
			String dbmsLoginPw = "1234";

			// 기사 등록
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
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

			String sql = "INSERT INTO article";
			sql += " SET regDate = NOW()";
			sql += ", updateDate = NOW()";
			sql += ", title = ?";
			sql += ", body = ?";
			sql += ", memberId = ?";
			sql += ", boardId = ?";

			try {
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, title);
				pstmt.setString(2, body);
				pstmt.setInt(3, memberId);
				pstmt.setInt(4, boardId);
				pstmt.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				id = rs.getInt(1);
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
		return id;
	}

}
