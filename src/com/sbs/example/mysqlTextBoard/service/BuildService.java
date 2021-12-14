package com.sbs.example.mysqlTextBoard.service;

import java.util.List;

import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.util.Util;

public class BuildService {
	
	private ArticleService articleService;
	private MemberService  memberService;
	
	public BuildService() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void buildSite() {
		System.out.println("기존 폴더 삭제");
		Util.rmdir("site");
		System.out.println("site/article 폴더 생성");
		Util.mkdirs("site");
		
		List<Article> articles = articleService.getArticles();
		
		String head = Util.getFileContents("site_template/head.html");
		String foot = Util.getFileContents("site_template/foot.html");
		
		for (Article article : articles) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(head);

			sb.append("번호 : " + article.id + "<br>");
			sb.append("작성날짜 : " + article.regDate + "<br>");
			sb.append("갱신날짜 : " + article.updateDate + "<br>");
			sb.append("제목 : " + article.title + "<br>");
			sb.append("내용 : " + article.body+ "<br>");
			sb.append("<a href=\"article_detail_" + (article.id -1)+ ".html\">이전 글</a><br>");
			sb.append("<a href=\"article_detail_" + (article.id +1)+ ".html\">다음 글</a><br>");
			sb.append("</div>");
			
			sb.append(foot);
			
			String fileName	= "article_detail_" + article.id + ".html"; 
			String filePath = "site/article/";
			Util.writeFile("site/" + fileName, sb.toString());
			System.out.println(filePath + "생성");
			
			
		}
	}

}
