package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import article.model.ArticleContent;
import jdbc.JdbcUtil;

public class ArticleContentDao {
	
	public void delete(Connection con, int no) throws SQLException {
		String sql = "DELETE FROM project_article_content WHERE article_no=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, no);
		}
	}
	
	public int update(Connection con, int no, String content) throws SQLException {
		String sql = "UPDATE project_article_content SET content=? WHERE article_no=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}

	public ArticleContent insert(Connection con, ArticleContent articleContent) throws Exception {
		String sql = "INSERT INTO project_article_content (article_no, content) "
				+ "VALUES (?, ?)";
		
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, articleContent.getNumber());
			pstmt.setString(2, articleContent.getContent());
			int cnt = pstmt.executeUpdate();
			
			if (cnt == 1) {
				return articleContent;
			} else {
				return null;
			}
		}
	}
	
	public ArticleContent selectById(Connection con, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select * from project_article_content where article_no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			ArticleContent content = null;
			if (rs.next()) {
				content = new ArticleContent(rs.getInt("article_no"), rs.getString("content"));
			} return content;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

}
