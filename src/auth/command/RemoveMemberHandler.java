package auth.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.InvalidPasswordException;
import auth.service.RemoveMemberService;
import auth.service.User;
import mvc.command.CommandHandler;

public class RemoveMemberHandler implements CommandHandler{
	private static final String FORM_VIEW = "removeMemberForm";
	private RemoveMemberService removeMemberService = new RemoveMemberService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String password = req.getParameter("password");
		User user = (User) req.getSession().getAttribute("authUser");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if (password == null || password.isEmpty()) {
			errors.put("password", true);
		}
		
		if (req.getParameter("confirmDelete") == null || req.getParameter("confirmDelete").isEmpty()) {
			errors.put("noCheck", true);
		}
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			removeMemberService.removeMember(user.getUserId(), password);
			req.getSession().invalidate();
			res.sendRedirect(req.getContextPath()+"/index.jsp");
			return null;
		} catch (InvalidPasswordException e) {
			errors.put("wrongPw", true);
			return FORM_VIEW;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	
}
