package article.service;

import java.util.List;

import article.model.Article;

public class ArticlePage {
	
	private int total;
	private int currentPage;
	private List<Article> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public ArticlePage (int total, int currentPage, int size, List<Article> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		size = 5;
		
		if (total != 0) {
			this.totalPages = total / size;
			if (total%size>0) {
				totalPages++;
			}
			
			this.startPage = (currentPage-1) / size * size + 1;
			this.endPage = Math.min(startPage+size-1, totalPages);
		}
		
	}

	public int getTotal() {
		return total;
	}
	
	public boolean hasArticles() {
		return total > 0;
	}
	
	public boolean hasNoArticles() {
		return total == 0;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Article> getContent() {
		return content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
}
