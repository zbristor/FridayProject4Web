

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SkillServlet
 */
@WebServlet("/SkillServlet")
public class SkillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkillServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextURL="/ResumeOutput.jsp";
		String skill = request.getParameter("Skill");
		request.setAttribute("Skill", skill);
		String decision = request.getParameter("skillInput");
		request.setAttribute("skillInput", decision);
		if(decision.equals("yes"))
		{
			response.sendRedirect("SkillInput.html");
		}
		else if(decision.equals("no"))
		{
			//response.sendRedirect("WorkInput.html");
		}
		
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
		
		
	}

}
