package com.sbs.example.mysqlTextBoard.dao;

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
}
