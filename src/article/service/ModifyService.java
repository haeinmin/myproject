package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class ModifyService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao articleContentDao = new ArticleContentDao();
	
	public void modify(ModifyRequest modReq) {
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Article article = articleDao.selectById(con, modReq.getArticleNumber());
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			
			ArticleContent articleContent = articleContentDao.selectById(con, modReq.getArticleNumber());
			if (articleContent == null) {
				throw new ArticleContentNotFoundException();
			}
			
			if (!canModify(modReq.getUserId(), article)) {
				throw new PermissionDeniedException();
			}
			
			articleDao.update(con, modReq.getArticleNumber(), modReq.getTitle());
			articleContentDao.update(con, modReq.getArticleNumber(), modReq.getContent());
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(con);
			throw e;
		} finally {
			JdbcUtil.close(con);
		}
	}


	private boolean canModify(String modifyingUserId, Article article) {
		return article.getWriter().getId().equals(modifyingUserId);
	}
}