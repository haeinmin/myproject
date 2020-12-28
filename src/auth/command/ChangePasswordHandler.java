package auth.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.ChangePasswordService;
import auth.service.InvalidPasswordException;
import auth.service.MemberNotFoundException;
import auth.service.User;
import mvc.command.CommandHandler;

public class ChangePasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "changePwForm";
	private ChangePasswordService changePwService = new ChangePasswordService();
	
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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
		String curPw = req.getParameter("curPw");
		String newPw = req.getParameter("newPw");
		User user = (User) req.getSession().getAttribute("authUser");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if (curPw == null || curPw.isEmpty()) {
			errors.put("curPw", true);
		}
		
		if (newPw == null || newPw.isEmpty()) {
			errors.put("newPw", true);
		}
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			changePwService.changePassword(user.getUserId(), curPw, newPw);
			res.sendRedirect(req.getContextPath() + "/index.jsp");
			return null;
		} catch (MemberNotFoundException e) {
			return FORM_VIEW;
		} catch (InvalidPasswordException e) {
			errors.put("wrongPw", true);
			return FORM_VIEW;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

}
