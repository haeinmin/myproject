package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class JoinService {
	private MemberDao memberDao = new MemberDao();
	
	public User join(JoinRequest joinReq) throws Exception {
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Member mem = memberDao.selectById(con, joinReq.getId());
			if (mem != null) {
				JdbcUtil.rollback(con);
				throw new DuplicatedIdException();
			}
			
			Member member = new Member();
			member.setId(joinReq.getId());
			member.setName(joinReq.getName());
			member.setPassword(joinReq.getPassword());
			
			memberDao.insert(con, member);
			con.commit();
			return new User(member.getId(), member.getName());
		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con);
		}
	}

}
