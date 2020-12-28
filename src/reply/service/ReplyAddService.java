package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import reply.dao.ReplyDao;
import reply.model.Reply;

public class ReplyAddService {
	private ReplyDao dao = new ReplyDao();
	
	public void add(String userId, int articleNo, String body) {
		Connection con = ConnectionProvider.getConnection();
		
		try {
			dao.insert(con, userId, articleNo, body);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
