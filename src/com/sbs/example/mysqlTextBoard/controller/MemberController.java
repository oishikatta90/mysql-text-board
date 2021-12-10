package com.sbs.example.mysqlTextBoard.controller;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.service.MemberService;

public class MemberController extends Controller{
	MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if (cmd.startsWith("member join")) {
			doJoin(cmd);
		}
	}

	private void doJoin(String cmd) {
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