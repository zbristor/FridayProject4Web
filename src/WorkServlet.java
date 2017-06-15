

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		String nextURL="/SkillServlet";
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
		String decision = request.getParameter("Input");
		request.setAttribute("WorkInput",decision);
		String endWork = position +" "+ company + " "+sDate+" "+eDate+" "+duties;
		ArrayList<String> workArray = (ArrayList<String>) session.getAttribute("WorkArray");
		workArray.add(endWork);
		if(decision.equals("yes"))
		{
			nextURL="/WorkInput.html";
		}
		//Person p = new Person(session.getAttribute("FirstName").toString(), session.getAttribute("LastName").toString(),session.getAttribute("Email").toString(), 
				//session.getAttribute("EduArray").toString(), session.getAttribute("WorkArray").toString(), session.getAttribute("SkillArray").toString());
		else if(decision.equals("no"))
		{
			nextURL="/SkillInput.html";
		}
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	}

}
