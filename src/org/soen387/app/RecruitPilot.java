package org.soen387.app;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.soen387.domain.model.pilot.Pilot;
import org.soen387.domain.model.pilot.mapper.PilotMapper;
import org.soen387.domain.model.pilot.tdg.PilotTDG;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.Player;
import org.soen387.domain.model.player.mapper.PlayerMapper;
import org.soen387.domain.model.user.User;
import org.soen387.domain.model.user.tdg.UserTDG;

/**
 * Servlet implementation class RecruitPilot
 */
@WebServlet("/RecruitPilot")
public class RecruitPilot extends AbstractPageController implements Servlet {
	private static final long serialVersionUID = 1L;
	private static int pilotNumber = 0;

	/**
	 * @see AbstractPageController#AbstractPageController()
	 */
	public RecruitPilot() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setupRequest(request);

		try {
			User u = (User) request.getSession().getAttribute("user");

			if (u != null) {
				
				IPlayer p = PlayerMapper.find(u);
				String pilotName = "Pilot" + pilotNumber;
				pilotNumber++;
				
				long max_id = PilotTDG.getMaxId();
				Pilot newPilot = new Pilot(max_id, 1, pilotName , p);
				PilotMapper.insert(newPilot);

				// Commit
				DbRegistry.getDbConnection().commit();
				
				Pilot recruitedPilot = PilotMapper.find(max_id);
				
				request.setAttribute("pilot", recruitedPilot);
				request.setAttribute("player", recruitedPilot.getPlayer());

				// Forward to a jsp, make sure you fill it in properly
				request.getRequestDispatcher("/WEB-INF/jsp/xml/RecruitPilotSuccess.jsp").forward(request, response);
			}
		} catch (Exception e) {
			forwardError(request, response, e.getMessage());
			e.printStackTrace();
		} finally {
			teardownRequest();
		}

	}

}
