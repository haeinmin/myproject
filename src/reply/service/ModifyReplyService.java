package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.service.PermissionDeniedException;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import reply.dao.ReplyDao;
import reply.model.Reply;

public class ModifyReplyService {
	private ReplyDao replyDao = new ReplyDao();
	
	public void modify(ModifyReplyRequest modReq) {
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Reply reply = replyDao.selectById(con, modReq.getReplyNo());
			
			if (!canModify(modReq.getUserId(), reply)) {
				throw new PermissionDeniedException();
			}
			
			replyDao.update(con, modReq.getReplyNo(), modReq.getBody());
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

	private boolean canModify(String modifyingUserId, Reply reply) {
		return reply.getMemberId().equals(modifyingUserId);
	}

}
