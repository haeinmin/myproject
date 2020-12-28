package visit.service;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import visit.dao.VisitCountDao;

/**
 * Application Lifecycle Listener implementation class VisitSessionListener
 *
 */
@WebListener
public class VisitSessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public VisitSessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         if(se.getSession().isNew()) {
        	 execute(se);
         }
    }

	private void execute(HttpSessionEvent se) {
		VisitCountDao dao = VisitCountDao.getInstance();
		
		try {
			dao.setTotalCount();
			int totalCount = dao.getTotalCount();
			int todayCount = dao.getTodayCount();
			HttpSession session = se.getSession();
			
			session.setAttribute("totalCount", totalCount);
			session.setAttribute("todayCount", todayCount);
		} catch (Exception e) {
			System.out.println("========방문자 카운터 오류==========");
			e.printStackTrace();
		}
		
	}

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }
	
}
