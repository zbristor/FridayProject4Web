

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		String nextURL="/ResumeOutput.jsp";
		String skill = request.getParameter("Skill");
		request.setAttribute("Skill", skill);
		String decision = request.getParameter("skillInput");
		request.setAttribute("skillInput", decision);
		ArrayList<String> skillArray = (ArrayList<String>) session.getAttribute("SkillArray");
		skillArray.add(skill);
		if(decision.equals("yes"))
		{
			nextURL="/SkillInput.html";
		}
		else if(decision.equals("no"))
		{
			nextURL="/ResumeOutput.jsp";
		}
		
		String fname = session.getAttribute("FirstName").toString();
		String lname = session.getAttribute("LastName").toString();
		String email = session.getAttribute("Email").toString();
		
		ArrayList<Education> eduArray = (ArrayList<Education>) session.getAttribute("EduArray");
		ArrayList<String> workArray = (ArrayList<String>) session.getAttribute("WorkArray");
		String str = "";
		Person p = new Person(fname, lname, email, eduArray, workArray, skillArray);
		for(int i=0; i<eduArray.size(); i++)
		{
			String degree = eduArray.get(i).getDegree();
			String school = eduArray.get(i).getSchool();
			String year = eduArray.get(i).getYear();
			String comb = degree+"\n"+school+", "+year+"\n";
			str=str+comb;
		}
		session.setAttribute("Str", str);
		
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
		

		

		
		
		
	}

}
