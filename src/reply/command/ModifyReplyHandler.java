package reply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import reply.service.ModifyReplyRequest;
import reply.service.ModifyReplyService;

public class ModifyReplyHandler implements CommandHandler {
	private ModifyReplyService modifyReplyService = new ModifyReplyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User) req.getSession().getAttribute("authUser");
		
		int replyNo = Integer.parseInt((String) req.getSession().getAttribute("replyNo"));
		String userId = user.getUserId();
		String body = req.getParameter("body");
		ModifyReplyRequest modReq = new ModifyReplyRequest(userId, replyNo, body);
		modifyReplyService.modify(modReq);
		
		return "modifyReplySuccess";
	}
	
}
