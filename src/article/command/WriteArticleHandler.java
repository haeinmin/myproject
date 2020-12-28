package article.command;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Writer;
import article.model.Article;
import article.service.ArticleData;
import article.service.ArticlePage;
import article.service.ReadArticleService;
import article.service.WriteArticleService;
import article.service.WriteRequest;
import auth.service.User;
import mvc.command.CommandHandler;

public class WriteArticleHandler implements CommandHandler{
	private static final String FORM_VIEW = "writeArticleForm";
	private WriteArticleService writeArticleService = new WriteArticleService();
	private ReadArticleService readService = new ReadArticleService();
	
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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User) req.getSession(false).getAttribute("authUser");
		WriteRequest writeRequest = createWriteRequest(user, req);
		
		writeRequest.validate(errors);
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
			int articleNo = writeArticleService.write(writeRequest);
			req.setAttribute("articleNo", articleNo);
			ArticleData articleData = readService.getArticle(articleNo, true);
			req.setAttribute("articleData", articleData);
			return "readArticle";
	}

	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(
				new Writer(user.getUserId(), user.getName()), 
				req.getParameter("title"), 
				req.getParameter("content"),
				req.getParameter("type"));
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
}
