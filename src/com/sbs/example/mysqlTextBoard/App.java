package com.sbs.example.mysqlTextBoard;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.container.Container;

public class App {
	public static void run() {
		Scanner scan = Container.scanner;

		while (true) {
			System.out.print("명령어 >>");
			String cmd = scan.nextLine();

			if (cmd.equals("article list")) {
				System.out.println(" ==게시물 리스트== ");
			} else if (cmd.equals("system exit")) {
				System.out.println(" ==시스템 종료==");
				break;
			}
		}
	}

}
