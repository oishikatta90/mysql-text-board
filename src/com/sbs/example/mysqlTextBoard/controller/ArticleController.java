package com.sbs.example.mysqlTextBoard.controller;

import java.util.List;
import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.Board;
import com.sbs.example.mysqlTextBoard.dto.Member;
import com.sbs.example.mysqlTextBoard.service.ArticleService;
import com.sbs.example.mysqlTextBoard.service.MemberService;

public class ArticleController extends Controller {
	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController() {
		memberService = Container.memberService;
		articleService = Container.articleService;
	}

	public void doCommand(String cmd) {
		if (cmd.startsWith("article list")) {
			showList(cmd);
		} else if (cmd.startsWith("article detail")) {
			showDetail(cmd);
		} else if (cmd.startsWith("article delete")) {
			doDelete(cmd);
		} else if (cmd.startsWith("article write")) {
			doWrite(cmd);
		} else if (cmd.startsWith("article modify")) {
			doModify(cmd);
		}
	}

	private void doModify(String cmd) {
		System.out.println(" ==게시물 수정! ==");
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(inputedId);
		if (article == null) {
			System.out.println("존재하지는 게시물 번호입니다.");
			return;
		}
		
		if (article.memberId != Container.session.getLoginedMemberId()) {
			System.out.println("수정 할 권한이 없습니다.");
			return;
		}


		Member member = memberService.getMemberById(article.memberId);
		String writer = member.name;

		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성날짜 : %s\n", article.regDate);
		System.out.printf("작성자 : %s\n", writer);

		Scanner scan = Container.scanner;

		System.out.print("제목 : ");
		String title = scan.nextLine();

		System.out.print("내용 : ");
		String body = scan.nextLine();

		articleService.modify(inputedId, title, body);

		System.out.printf("%d번 게시물을 수정하였습니다.\n", inputedId);
	}

	private void doWrite(String cmd) {
		Scanner scan = Container.scanner;
		System.out.println(" ==게시물 작성하기 ==");

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		System.out.print("제목 : ");
		String title = scan.nextLine();

		System.out.print("내용 : ");
		String body = scan.nextLine();

		int memberId = Container.session.getLoginedMemberId();
		int boardId = 1;

		int id = articleService.write(title, body, memberId, boardId);

		System.out.printf("%d번 게시물을 생성하였습니다.\n", id);
	}

	private void doDelete(String cmd) {
		System.out.println(" ==게시물 삭제하기 ==");
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(inputedId);

		if (article == null) {
			System.out.println("존재하지는 게시물 번호입니다.");
			return;
		}
		
		if (article.memberId != Container.session.getLoginedMemberId()) {
			System.out.println("삭제 할 권한이 없습니다.");
			return;
		}
		articleService.delete(inputedId);

		System.out.printf("%d번 게시물을 삭제하였습니다.", inputedId);
	}

	private void showList(String cmd) {
		String boardCode = Container.session.getCurrentBoardCode();
		Board board = articleService.getBoardByCode(boardCode);
		System.out.printf(" == %s 게시물 리스트== \n", board.name);

		List<Article> articles = articleService.getForPrintArticles(board.id);

		System.out.println("번호 / 작성 / 수정 / 작성자 / 제목 / 내용");

		for (Article article : articles) {
			String writer = article.extra__writer;
			System.out.printf("%d / %s / %s / %s / %s / %s\n", article.id, article.regDate, article.updateDate, writer,
					article.title, article.body);
		}
	}

	private void showDetail(String cmd) {
		System.out.println(" ==게시물 상세 정보 ==");
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(inputedId);

		if (article == null) {
			System.out.println("존재하지는 게시물 번호입니다.");
			return;
		}

		Member member = memberService.getMemberById(article.memberId);
		String writer = member.name;

		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성날짜 : %s\n", article.regDate);
		System.out.printf("수정날짜 : %s\n", article.updateDate);
		System.out.printf("타이틀 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
		System.out.printf("작성자 : %s\n", writer);
	}

}
