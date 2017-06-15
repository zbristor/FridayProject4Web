

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WorkServlet
 */
@WebServlet("/WorkServlet")
public class WorkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextURL="/ResumeOutput.jsp";
		String position = request.getParameter("Position");
		request.setAttribute("Position", position);
		String company = request.getParameter("Company");
		request.setAttribute("Company", company);
		String sDate = request.getParameter("StartDate");
		request.setAttribute("StartDate", sDate);
		String eDate = request.getParameter("EndDate");
		request.setAttribute("EndDate", eDate);
		String duties = request.getParameter("Duties");
		request.setAttribute("Duties", duties);
		String workDecision = request.getParameter("workInput");
		request.setAttribute("WorkInput",workDecision);
		if(workDecision.equals("yes"))
		{
			response.sendRedirect("WorkInput.html");
		}
		else if(workDecision.equals("no"))
		{
			response.sendRedirect("SkillInput.html");
		}
		//getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	}

}
