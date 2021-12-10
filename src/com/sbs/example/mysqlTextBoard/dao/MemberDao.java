package com.sbs.example.mysqlTextBoard.dao;

import java.util.Map;

import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.Member;
import com.sbs.example.mysqlTextBoard.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlTextBoard.mysqlutil.SecSql;

public class MemberDao {

	public int add(String loginId, String loginPw, String name) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO `member`");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", name);

		return MysqlUtil.insert(sql);
	}

	public Member getMemberById(int memberId) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE id = ?", memberId);

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		try {
			if (map.isEmpty()) {
				return null;
			}
		} catch (Exception e) {
			System.out.println("해당 번호는 없습니다.\n 프로그램을 종료합니다.");
			System.exit(0);
		}
		return new Member(map);
	}

	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		try {
			if (map.isEmpty()) {
				return null;
			}
		} catch (Exception e) {
			System.out.println("해당 아이디는 없습니다.\n 프로그램을 종료합니다.");
			System.exit(0);
		}
		return new Member(map);
	}
}
