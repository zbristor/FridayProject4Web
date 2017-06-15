

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ResumeServlet
 */
@WebServlet("/EducationServlet")
public class EducationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EducationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nextURL="/WorkServlet";
		String firstName = session.getAttribute("FirstName").toString();
		String lastName = session.getAttribute("LastName").toString();
		String email = session.getAttribute("Email").toString();
		ArrayList<Education> eduArray = (ArrayList<Education>) session.getAttribute("EduArray");
		ArrayList<String> workArray = (ArrayList<String>) session.getAttribute("WorkArray");
		ArrayList<String> skillArray = (ArrayList<String>) session.getAttribute("SkillArray");
		Person p = new Person(firstName, lastName, email, eduArray, workArray, skillArray);
		String degree = request.getParameter("Degree");
		request.setAttribute("Degree", degree);
		String school = request.getParameter("School");
		request.setAttribute("School", school);
		String year = request.getParameter("Year");
		request.setAttribute("Year", year);
		String decision = request.getParameter("Input");
		request.setAttribute("eduInput", decision);
		Education e = new Education(degree, school, year);
		p.addEdu(e);
		
		if(decision.equals("yes"))
		{
			nextURL="/EducationInput.html";
		}
		else if(decision.equals("no"))
		{
			nextURL="/WorkInput.html";
		}
		
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	
	}


}
