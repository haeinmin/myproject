package member.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.dao.MemberDao;
import member.model.Member;
import mvc.command.CommandHandler;

public class MemberListHandler implements CommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		MemberDao dao = new MemberDao();
		ArrayList<Member> memberList = dao.getMemberList();
		
		req.setAttribute("memberList", memberList);
		
		return "memberListForm";
	}
}
