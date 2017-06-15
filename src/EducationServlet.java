

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
@WebServlet("/ResumeServlet")
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
		
		String nextURL="/WorkServlet";
		
		HttpSession session = request.getSession();
		ArrayList<String> eduArray = new ArrayList<String>();
		session.setAttribute("EduArray", eduArray);
		ArrayList<String> workArray = new ArrayList<String>();
		session.setAttribute("WorkArray", workArray);
		ArrayList<String> skillArray = new ArrayList<String>();
		session.setAttribute("SkillArray", skillArray);
		
		String firstName = request.getParameter("FirstName");
		request.setAttribute("LastName", firstName);
		String lastName = request.getParameter("LastName");
		request.setAttribute("LastName", lastName);
		String email = request.getParameter("Email");
		request.setAttribute("Email", email);
		
		
		Person p = new Person(firstName, lastName, eduArray, workArray, skillArray);
		
		String degree = request.getParameter("Degree");
		request.setAttribute("Degree", degree);
		String school = request.getParameter("School");
		request.setAttribute("School", school);
		String year = request.getParameter("Year");
		request.setAttribute("Year", year);
		String decision = request.getParameter("Input");
		request.setAttribute("eduInput", decision);
		p.addEdu(degree+"/n"+school+", "+year);
		if(decision.equals("yes"))
		{
			response.sendRedirect("EducationInput.html");
		}
		else if(decision.equals("no"))
		{
			response.sendRedirect("WorkInput.html");
		}
		
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	
	}


}
