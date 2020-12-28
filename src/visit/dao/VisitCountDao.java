package visit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class VisitCountDao {
	private static VisitCountDao instance;
	
	private VisitCountDao() {}
	public static VisitCountDao getInstance() {
		if (instance == null) {
			instance=new VisitCountDao();
		}
		return instance;
	}
	
	public void setTotalCount() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO visit (v_date) VALUES (sysdate)";
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}
	
	public int getTotalCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS totalcnt FROM VISIT";
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				totalCount = rs.getInt("totalcnt");
			} 
			return totalCount;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		
	}
	
	public int getTodayCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int todayCount = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS todaycnt FROM VISIT WHERE)=TO_DATE(SYSDATE, 'YYYY-MM-DD')";
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				todayCount = rs.getInt("todaycnt");
			} 
			return todayCount;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}
	

}
