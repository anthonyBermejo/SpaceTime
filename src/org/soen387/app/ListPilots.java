package org.soen387.app;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.soen387.domain.model.pilot.IPilot;
import org.soen387.domain.model.pilot.mapper.PilotMapper;

/**
 * Servlet implementation class ListPilots
 */
@WebServlet("/ListPilots")
public class ListPilots extends AbstractPageController implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see AbstractPageController#AbstractPageController()
     */
    public ListPilots() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		setupRequest(request);
		
		try {
			
			//Do Stuff
			List<IPilot> pilotList = PilotMapper.findAll();
			
			//Commit
			//DbRegistry.getDbConnection().commit();
			
			request.setAttribute("pilots", pilotList);
			
			//Forward to a jsp, make sure you fill it in properly
			request.getRequestDispatcher("/WEB-INF/jsp/xml/ListPilotsSuccess.jsp").forward(request, response);
		} catch (Exception e) {
			forwardError(request, response, e.getMessage());
			e.printStackTrace();
		} finally {
			teardownRequest();	
		}
		
		
		
	}


}
