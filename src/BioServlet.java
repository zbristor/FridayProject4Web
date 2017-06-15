

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BioServlet
 */
@WebServlet("/BioServlet")
public class BioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextURL = "/EducationInput.html";
		HttpSession session = request.getSession();
		ArrayList<Education> eduArray = new ArrayList<Education>();
		session.setAttribute("EduArray", eduArray);
		ArrayList<String> workArray = new ArrayList<String>();
		session.setAttribute("WorkArray", workArray);
		ArrayList<String> skillArray = new ArrayList<String>();
		session.setAttribute("SkillArray", skillArray);
		
		String firstName = request.getParameter("FirstName");
		session.setAttribute("FirstName", firstName);
		String lastName = request.getParameter("LastName");
		session.setAttribute("LastName", lastName);
		String email = request.getParameter("Email");
		session.setAttribute("Email", email);
		
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	}

}
