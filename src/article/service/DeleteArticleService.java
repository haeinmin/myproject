package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class DeleteArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao articleContentDao = new ArticleContentDao();
	private MemberDao memberDao = new MemberDao();
	
	public void remove(int no, String password, User authUser) {
		Connection con = ConnectionProvider.getConnection();
		
		try {
			con.setAutoCommit(false);
			
			Member member = memberDao.selectById(con, authUser.getUserId());
			
			if (!member.getPassword().equals(password)) {
				throw new PermissionDeniedException();
			}
			
			articleDao.delete(con, no);
			articleContentDao.delete(con, no);
			con.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		}
	}
}
