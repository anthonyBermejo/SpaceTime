package org.soen387.app;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

/**
 * Servlet implementation class AddPilotToTeam
 */
@WebServlet("/AddPilotToTeam")
public class AddPilotToTeam extends AbstractPageController implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see AbstractPageController#AbstractPageController()
     */
    public AddPilotToTeam() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		setupRequest(request);
		
		try {
			
			//Do Stuff
			
			//Commit
			DbRegistry.getDbConnection().commit();
			
			//Forward to a jsp, make sure you fill it in properly
			request.getRequestDispatcher("/WEB-INF/jsp/xml/").forward(request, response);
		} catch (Exception e) {
			forwardError(request, response, e.getMessage());
			e.printStackTrace();
		} finally {
			teardownRequest();	
		}
		
		
		
	}


}
