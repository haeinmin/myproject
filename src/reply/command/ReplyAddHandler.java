package reply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.ReadArticleService;
import auth.service.User;
import mvc.command.CommandHandler;
import reply.service.ReplyAddService;

public class ReplyAddHandler implements CommandHandler{
private ReplyAddService addService = new ReplyAddService();
private ReadArticleService readService = new ReadArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User) req.getSession().getAttribute("authUser");
		
		int articleNo = Integer.parseInt(req.getParameter("no"));
		String userId = user.getUserId();
		String body = req.getParameter("body");
		addService.add(userId, articleNo, body);
		
//		req.setAttribute("articleNo", articleNo);
//		ArticleData articleData = readService.getArticle(articleNo, true);
//		req.setAttribute("articleData", articleData);
//		return "readArticle";
		return "replyAddSuccess";
	}
}
