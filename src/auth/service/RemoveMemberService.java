package auth.service;

import java.sql.Connection;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class RemoveMemberService {
	private MemberDao memberDao = new MemberDao();
	
	public void removeMember(String id, String password) {
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Member member = memberDao.selectById(con, id);
			if (member == null) {
				throw new MemberNotFoundException();
			}
			
			if (!member.matchPassword(password)) {
				throw new InvalidPasswordException();
			}
			
			memberDao.delete(con, member);
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	

}
