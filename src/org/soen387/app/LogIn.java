package org.soen387.app;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.domain.model.user.User;
import org.soen387.domain.model.user.mapper.UserMapper;

/**
 * Servlet implementation class ListGames
 */
@WebServlet("/LogIn")
public class LogIn extends AbstractPageController implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see AbstractPageController#AbstractPageController()
     */
    public LogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		setupRequest(request);
		
		try {
			//login
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			User u = UserMapper.find(username, password);
			
			if (u != null) {
				request.setAttribute("user", u);	
				request.getSession().setAttribute("user", u);
				request.getRequestDispatcher("/WEB-INF/jsp/xml/LogInSuccess.jsp").forward(request, response);
			}
		} catch (Exception e) {
			forwardError(request, response, e.getMessage());
			e.printStackTrace();
		} finally {
			teardownRequest();	
		}	
	}


}
