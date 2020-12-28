package article.service;

import article.model.Article;
import article.model.ArticleContent;

public class ArticleData {
	private Article article;
	private ArticleContent articleContent;
	
	public ArticleData(Article article, ArticleContent articleContent) {
		this.article = article;
		this.articleContent = articleContent;
	}

	public Article getArticle() {
		return article;
	}

	public String getArticleContent() {
		return articleContent.getContent();
	}
	
}
