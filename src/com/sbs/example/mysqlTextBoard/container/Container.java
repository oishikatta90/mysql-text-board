package com.sbs.example.mysqlTextBoard.container;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.dao.ArticleDao;
import com.sbs.example.mysqlTextBoard.dao.MemberDao;
import com.sbs.example.mysqlTextBoard.service.ArticleService;
import com.sbs.example.mysqlTextBoard.service.MemberService;

public class Container {

	public static Scanner scanner;
	public static MemberService memberService;
	public static ArticleService articleService;
	public static MemberDao memberDao;
	public static ArticleDao articleDao;
	
	static {
		scanner = new Scanner(System.in);
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		
		memberService = new MemberService();
		articleService = new ArticleService();
	}

}
