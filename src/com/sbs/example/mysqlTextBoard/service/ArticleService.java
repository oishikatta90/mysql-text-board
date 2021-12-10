package com.sbs.example.mysqlTextBoard.service;

import java.util.List;

import com.sbs.example.mysqlTextBoard.dao.ArticleDao;
import com.sbs.example.mysqlTextBoard.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = new ArticleDao();
		}

	public List<Article> getArticles() {
		
		return articleDao.getArticles();
	}

	public Article getArticle(int inputedId) {
		return articleDao.getArticle(inputedId);
	}

	public int delete(int inputedId) {
		return articleDao.delete(inputedId);
		
	}

	public int write(String title, String body, int memberId, int boardId) {
		return articleDao.write(title, body, memberId, boardId);
	}

}
