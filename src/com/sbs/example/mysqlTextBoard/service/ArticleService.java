package com.sbs.example.mysqlTextBoard.service;

import java.util.List;

import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.dao.ArticleDao;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.Board;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
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

	public int modify(int inputedId, String title, String body) {
		return articleDao.modify(inputedId, title, body);
	}
	
	//출력용 데이터를 전달하기 위한 메소드
	public List<Article> getForPrintArticles(int boardId) {
		return articleDao.getForPrintArticles(boardId);
	}

	public Board getBoardByCode(String boardCode) {
		return articleDao.getBoardByCode(boardCode);
	}

	public boolean isMakeBoardAvailableName(String name) {
		Board board = articleDao.getBoardByName(name);
		return board == null;
	}

	public boolean isMakeBoardAvailableCode(String code) {
		Board board = articleDao.getBoardByCode(code);
		return board == null;
	}

	public int makeBoard(String name, String code) {
		return articleDao.makeBoard(name, code);
	}

}
