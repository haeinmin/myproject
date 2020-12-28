package reply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import article.model.Article;
import article.model.Writer;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.model.Member;
import reply.model.Reply;

public class ReplyDao {
	
	public List<Reply> listReply(Connection con, int articleNo) throws SQLException {
		String sql = "SELECT reply_no,"
				+ " writer_id,"
				+ " article_no,"
				+ " body,"
				+ " regdate " + 
				"FROM project_reply " + 
				"WHERE article_no=? " + 
				"ORDER BY reply_no DESC";

		List<Reply> list = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, articleNo);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Reply r = new Reply();
				r.setReplyNo(rs.getInt(1));
				r.setMemberId(rs.getString(2));
				r.setArticleNo(rs.getInt(3));
				r.setBody(rs.getString(4));
				r.setRegDate(rs.getTimestamp(5));
				
				list.add(r);
			}
		}
		return list;
	}
	
	public int update(Connection con, int no, String body) throws SQLException {
		String sql = "UPDATE project_reply SET body=? WHERE reply_no=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, body);
			pstmt.setInt(2, no);
			
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	

	public void insert(Connection con, String userId, int articleNo, String body) throws SQLException {
		String sql = "INSERT INTO project_reply (reply_no, writer_id, article_no, body, regDate) "
				+"VALUES (reply_no.nextval, ?, ?, ?, SYSDATE)";
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, articleNo);
			pstmt.setString(3, body);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(con);
		}
	}
	
	public Reply selectById(Connection con, int no) {
		Reply reply = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM project_reply WHERE reply_no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				reply = new Reply();
				reply.setReplyNo(rs.getInt(1));
				reply.setMemberId(rs.getString(2));
				reply.setArticleNo(rs.getInt(3));
				reply.setBody(rs.getString(4));
				reply.setRegDate(rs.getTimestamp(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		} return reply;
	}
}
