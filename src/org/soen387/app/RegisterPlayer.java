package org.soen387.app;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.soen387.domain.model.player.Player;
import org.soen387.domain.model.player.mapper.PlayerMapper;
import org.soen387.domain.model.player.tdg.PlayerTDG;
import org.soen387.domain.model.user.User;
import org.soen387.domain.model.user.mapper.UserMapper;
import org.soen387.domain.model.user.tdg.UserTDG;

/**
 * Servlet implementation class ListGames
 */
@WebServlet("/RegisterPlayer")
public class RegisterPlayer extends AbstractPageController implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see AbstractPageController#AbstractPageController()
     */
    public RegisterPlayer() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		setupRequest(request);
		
		try {
			
			//Do Stuff
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String firstname = request.getParameter("firstName");
			String lastname = request.getParameter("lastName");
			String email = request.getParameter("email");
			
			long max_id = UserTDG.getMaxId();
			User u = new User(max_id, 1, username, password);
			UserMapper.insert(u);
			
			//Commit
			DbRegistry.getDbConnection().commit();
			
			max_id = PlayerTDG.getMaxId();
			Player p = new Player(max_id, 1, firstname, lastname, email, u);
			PlayerMapper.insert(p);
			
			//Commit
			DbRegistry.getDbConnection().commit();
			
			request.setAttribute("user", u);
			request.setAttribute("player", p);
			
			//Forward to a jsp, make sure you fill it in properly
			request.getRequestDispatcher("/WEB-INF/jsp/xml/RegisterSuccess.jsp").forward(request, response);
		} catch (Exception e) {
			forwardError(request, response, e.getMessage());
			e.printStackTrace();
		} finally {
			teardownRequest();	
		}
		
		
		
	}


}
