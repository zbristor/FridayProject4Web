

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResumeServlet
 */
@WebServlet("/ResumeServlet")
public class ResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResumeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String nextURL="/ResumeOutput.jsp";
		String firstName=request.getParameter("FirstName");
		request.setAttribute("FirstName", firstName);
		String lastName=request.getParameter("LastName");
		request.setAttribute("LastName", lastName);
		String email = request.getParameter("Email");
		request.setAttribute("Email", email);
		String degree = request.getParameter("Degree");
		request.setAttribute("Degree", degree);
		String school = request.getParameter("School");
		request.setAttribute("School", school);
		String year = request.getParameter("Year");
		request.setAttribute("Year", year);
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
		
		
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
		
	}

}
