package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class WriteArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao articleContentDao = new ArticleContentDao();
	
	public Integer write(WriteRequest req) throws Exception {
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Article article = toArticle(req);
			Article savedArticle = articleDao.insert(con, article);
			if (savedArticle == null) {
				throw new RuntimeException("failed to insert article");
			}
			
			ArticleContent content = new ArticleContent(savedArticle.getNumber(), req.getContent());
			ArticleContent savedContent = articleContentDao.insert(con, content);
			if (savedContent == null) {
				throw new RuntimeException("failed to insert article content");
			}
			con.commit();
			return savedArticle.getNumber();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			JdbcUtil.rollback(con);
			throw e;
		} finally {
			JdbcUtil.close(con);
		}
	}

	private Article toArticle(WriteRequest req) {
		return new Article(null, req.getWriter(), req.getTitle(), null, null, 0, req.getType());
	}
}