package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import article.model.Article;
import article.model.Writer;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class ArticleDao {
	
	public void delete(Connection con, int no) throws SQLException {
		String sql = "DELETE FROM project_article WHERE article_no=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	public int update(Connection con, int no, String title) throws SQLException {
		String sql = "UPDATE project_article SET title=?, modDate=sysdate WHERE article_no=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setInt(2, no);
			
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectCount(Connection con) throws SQLException {
		String sql = "SELECT COUNT(*) FROM project_article";
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				return rs.getInt(1);
			} return 0;
		} finally {
			JdbcUtil.close(rs, stmt);
		}
	}
	public List<Article> select(Connection con, int pageNum, int size) throws SQLException {
		String sql = "SELECT rn, article_no, writer_id, writer_name, title, regdate, moddate, read_cnt, type "
				+"FROM (SELECT article_no, writer_id, writer_name, title, regdate, moddate, read_cnt, type, "
				+"ROW_NUMBER() OVER (ORDER BY article_no DESC) rn FROM project_article) WHERE rn BETWEEN ? AND ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (pageNum-1)*size+1);
			pstmt.setInt(2, pageNum*size);
			
			rs = pstmt.executeQuery();
			
			List<Article> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertArticle(rs));
			}
			
			return result;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}
	public Article selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * "
				+ "FROM project_article "
				+ "WHERE article_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			Article article = null;
			
			if (rs.next()) {
				article = convertArticle(rs);
			}
			return article;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}
	
	public void increaseReadCount(Connection con, int no) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement("update project_article set read_cnt = read_cnt+1 where article_no=?")) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
		
	private Article convertArticle(ResultSet rs) throws SQLException {
		return new Article(rs.getInt("article_no"), new Writer(rs.getString("writer_id"), rs.getString("writer_name")),
				rs.getString("title"), rs.getTimestamp("regdate"), rs.getTimestamp("moddate"), rs.getInt("read_cnt"), rs.getString("type"));
	}


	public Article insert(Connection con, Article article) throws Exception {
		String sql = "INSERT INTO project_article (article_no, writer_id, writer_name, title, regDate, modDate, read_cnt, type) " +
					 "VALUES (article_no.nextval, ?, ?, ?, sysdate, sysdate, 0, ?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql, new String[] {"article_no", "regDate", "modDate"});
			pstmt.setString(1, article.getWriter().getId());
			pstmt.setString(2, article.getWriter().getName());
			pstmt.setString(3, article.getTitle());
			pstmt.setString(4, article.getType());
			int cnt = pstmt.executeUpdate();
			
			if (cnt == 1) {
				rs = pstmt.getGeneratedKeys();
				int key = 0;
				Date regDate = null;
				Date modDate = null;
				if (rs.next()) {
					key = rs.getInt(1);
					regDate = rs.getTimestamp(2);
					modDate = rs.getTimestamp(3);
				}
				return new Article(key, article.getWriter(), article.getTitle(), regDate, modDate, 0, article.getType());
			} else {
				return null;
			}
		}  finally {
			JdbcUtil.close(pstmt);
		}
		
	}
}
