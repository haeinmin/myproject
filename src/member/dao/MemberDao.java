package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {
	
	public ArrayList<Member> getMemberList() throws SQLException {
		ArrayList<Member> memberList = new ArrayList<Member>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			String sql = "SELECT * FROM projectmember";
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				member = new Member();
				member.setId(rs.getString("memberId"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setRegDate(rs.getTimestamp("regDate"));
				memberList.add(member);
			}
			
			return memberList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}
	
	public void delete(Connection con, Member mem) {
		String sql = "DELETE FROM projectmember WHERE memberid=?";
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mem.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Connection con, Member mem) {
		String sql = "UPDATE projectmember SET name=?, password = ? WHERE memberid = ?";
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mem.getName());
			pstmt.setString(2, mem.getPassword());
			pstmt.setString(3, mem.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Member selectById(Connection con, String id) throws SQLException {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM projectmember WHERE memberid = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				member = new Member();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setRegDate(rs.getTimestamp(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		} return member;
	}
	
	public void insert(Connection con, Member mem) {
		String sql = "INSERT INTO projectmember (memberid, name, password, regDate) VALUES (?,?,?,SYSDATE)";
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mem.getPassword());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
