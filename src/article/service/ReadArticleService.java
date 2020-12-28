package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.ConnectionProvider;

public class ReadArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao articleContentDao = new ArticleContentDao();
	
	public ArticleData getArticle(int articleNum, boolean increaseReadCount) throws SQLException {
		try (Connection con = ConnectionProvider.getConnection()) {
			Article article = articleDao.selectById(con, articleNum);
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			ArticleContent articleContent = articleContentDao.selectById(con, articleNum);
			if (articleContent == null) {
				throw new ArticleContentNotFoundException();
			}
			
			if (increaseReadCount) {
				articleDao.increaseReadCount(con, articleNum);
			}
			return new ArticleData(article, articleContent);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
