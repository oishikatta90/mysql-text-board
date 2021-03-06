package com.sbs.example.mysqlTextBoard.container;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.controller.ArticleController;
import com.sbs.example.mysqlTextBoard.controller.BuildController;
import com.sbs.example.mysqlTextBoard.controller.Controller;
import com.sbs.example.mysqlTextBoard.controller.MemberController;
import com.sbs.example.mysqlTextBoard.dao.ArticleDao;
import com.sbs.example.mysqlTextBoard.dao.MemberDao;
import com.sbs.example.mysqlTextBoard.service.ArticleService;
import com.sbs.example.mysqlTextBoard.service.BuildService;
import com.sbs.example.mysqlTextBoard.service.MemberService;
import com.sbs.example.mysqlTextBoard.session.Session;

public class Container {

	public static Scanner scanner;
	public static MemberService memberService;
	public static ArticleService articleService;
	public static BuildService buildService;
	
	public static MemberDao memberDao;
	public static ArticleDao articleDao;
	
	public static Controller articleController;
	public static Controller memberController;
	public static Controller buildController;
	
	public static Session session;
	
	static {
		scanner = new Scanner(System.in);
		
		session = new Session();
		
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		
		memberService = new MemberService();
		articleService = new ArticleService();
		buildService = new BuildService();
		
		articleController = new ArticleController();
		memberController = new MemberController();
		buildController = new BuildController();
		
	}

}
