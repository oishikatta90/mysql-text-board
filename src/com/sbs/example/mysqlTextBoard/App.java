package com.sbs.example.mysqlTextBoard;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.controller.ArticleController;
import com.sbs.example.mysqlTextBoard.controller.Controller;
import com.sbs.example.mysqlTextBoard.controller.MemberController;
import com.sbs.example.mysqlTextBoard.mysqlutil.MysqlUtil;

public class App {
	public static void run() {
		Scanner scan = Container.scanner;

		while (true) {
			System.out.print("명령어 >>");
			String cmd = scan.nextLine();
			
			MysqlUtil.setDBInfo("localhost", "sbsst", "1234", "textboard");
			MysqlUtil.setDevMode(false);
			
			boolean needToExit = false;
			
			if (cmd.equals("system exit")) {
				System.out.println(" ==시스템 종료==");
				needToExit = true;
			}
			else {
				Controller controller = getControllerByCmd(cmd);
				if (controller != null) {
					controller.doCommand(cmd);
				}
			}
			MysqlUtil.closeConnection();
			if (needToExit == true) {
				break;
			}
		}
	}

	private static Controller getControllerByCmd(String cmd) {
		if (cmd.startsWith("article ")) {
			return Container.articleController;
		} else if (cmd.startsWith("member ")) {
			return Container.memberController;
		} else if (cmd.startsWith("build ")) {
			return Container.buildController;
		}
		return null;
	}
}
