package com.sbs.example.mysqlTextBoard;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.controller.ArticleController;

public class App {
	public static void run() {
		Scanner scan = Container.scanner;
		
		ArticleController articleController = new ArticleController();

		while (true) {
			System.out.print("명령어 >>");
			String cmd = scan.nextLine();

			if (cmd.equals("article list")) {
				articleController.showList();
				
			} else if (cmd.equals("system exit")) {
				System.out.println(" ==시스템 종료==");
				break;
			}
		}
	}

}
