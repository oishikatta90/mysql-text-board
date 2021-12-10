package com.sbs.example.mysqlTextBoard;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.controller.ArticleController;
import com.sbs.example.mysqlTextBoard.mysqlutil.MysqlUtil;

public class App {
	public static void run() {
		Scanner scan = Container.scanner;

		ArticleController articleController = new ArticleController();

		while (true) {
			System.out.print("명령어 >>");
			String cmd = scan.nextLine();
			
			MysqlUtil.setDBInfo("localhost", "sbsst", "1234", "textboard");
			
			
			boolean needToExit = false;
			
			if (cmd.startsWith("article ")) {
				articleController.doCommand(cmd);
			} else if (cmd.equals("system exit")) {
				System.out.println(" ==시스템 종료==");
				needToExit = true;
			}
			MysqlUtil.closeConnection();
			if (needToExit == true) {
				break;
			}
		}
	}
}
