package article.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleNotFoundException;
import article.service.ArticlePage;
import article.service.DeleteArticleService;
import article.service.ListArticleService;
import article.service.PermissionDeniedException;
import auth.service.User;
import mvc.command.CommandHandler;

public class DeleteArticleHandler implements CommandHandler{
	private static final String FORM_VIEW = "deleteArticleForm";
	private DeleteArticleService deleteService = new DeleteArticleService();
	private ListArticleService listArticleService = new ListArticleService();
	
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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		String password = req.getParameter("password");
		
		if (password == null || password.isEmpty()) {
			errors.put("password", true);
		}
		
		if(!password.equals(req.getParameter("password"))) {
			errors.put("invalidPassword", true);
		}
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			deleteService.remove(no, password, authUser);
			String pageNoVal = req.getParameter("pageNo");
			int pageNo = 1;
			if (pageNoVal != null) {
				pageNo = Integer.parseInt(pageNoVal);
			}
			ArticlePage articlePage = listArticleService.getArticlePage(pageNo);
			req.setAttribute("articlePage", articlePage);
			return "listArticle";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		
		
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
}
