package com.sbs.example.mysqlTextBoard.controller;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.dto.Member;
import com.sbs.example.mysqlTextBoard.service.MemberService;
import com.sbs.example.mysqlTextBoard.session.Session;

public class MemberController extends Controller {
	MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if (cmd.startsWith("member join")) {
			doJoin(cmd);
		} else if (cmd.startsWith("member login")) {
			doLogin(cmd);
		} else if (cmd.startsWith("member logout")) {
			doLogout(cmd);
		} else if (cmd.startsWith("member whoami")) {
			showWhoami(cmd);
		}
	}

	private void showWhoami(String cmd) {
		System.out.println(" == 회원확인 ==");

		if (!Container.session.isLogined()) {
			System.out.println("로그인 후 이용해주세요!");
			return;
		}

		int loginedMemberId = Container.session.getLoginedMemberId();
		Member member = memberService.getMemberById(loginedMemberId);
		System.out.printf("번호 : %d", member.id);
		System.out.printf("\n가입날 : %s", member.regDate);
		System.out.printf("\n로그인 아이디 : %s", member.loginId);
		System.out.printf("\n이름 : %s\n", member.name);
	}

	private void doLogout(String cmd) {
		if (!Container.session.isLogined()) {
			System.out.println("로그인 후 이용해주세요!");
			return;
		}
		Container.session.logOut();
		System.out.println("로그아웃 되었습니다.");

	}

	private void doLogin(String cmd) {
		if (Container.session.isLogined()) {
			System.out.println("로그아웃 후 이용해 주세요.");
			return;
		}
		Scanner scan = Container.scanner;
		System.out.println(" == 로그인 ==");

		System.out.print("로그인 아이디 : ");
		String loginId = scan.nextLine().trim();

		if (loginId.length() == 0) {
			System.out.println("로그인 아이디를 입력해주세요!");
			return;
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("존재하지 않는 회원입니다.");
			return;
		}

		System.out.print("비밀번호  : ");
		String loginPw = scan.nextLine().trim();
		if (loginPw.length() == 0) {
			System.out.println("비밀번호를 입력해주세요!");
			return;
		}

		if (!member.loginPw.equals(loginPw)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}

		Container.session.logIn(member.id);

		System.out.printf("%s 님 환영합니다.\n", member.name);
	}

	private void doJoin(String cmd) {
		if (Container.session.isLogined()) {
			System.out.println("로그아웃 후 이용해 주세요.");
			return;
		}
		Scanner scan = Container.scanner;
		System.out.println(" ==회원가입! ==");

		System.out.print("로그인 아이디 : ");
		String loginId = scan.nextLine().trim();

		if (loginId.length() == 0) {
			System.out.println("로그인 아이디를 입력해주세요!");
			return;
		}
		System.out.print("비밀번호  : ");
		String loginPw = scan.nextLine().trim();
		if (loginPw.length() == 0) {
			System.out.println("비밀번호를 입력해주세요!");
			return;
		}
		System.out.print("비밀번호 확인 : ");
		String loginPwConfirm = scan.nextLine().trim();
		if (loginPwConfirm.length() == 0) {
			System.out.println("비밀번호를 입력해주세요!");
			return;
		}
		if (!loginPw.equals(loginPwConfirm)) {
			System.out.println("비밀번호가 일치하지 않습니다!");
			return;
		}
		System.out.print("이름 : ");
		String name = scan.nextLine().trim();
		if (name.length() == 0) {
			System.out.println("이름을 입력해주세요!");
			return;
		}

		int id = memberService.join(loginId, loginPwConfirm, name);
		System.out.printf("%d번 회원을 생성하였습니다.\n", id);
	}
}