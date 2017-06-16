

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Provides the user with a choice to skip adding work and to go straight to skills servlet", urlPatterns = { "/Choice" })
public class ChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChoiceServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextURL = "/WorkInput.html";
		String decision = request.getParameter("Input");
		request.setAttribute("Input",decision);
		if(decision.equals("yes"))
			nextURL = "/WorkInput.html";
		else if (decision.equals("no"))
			nextURL = "/SkillInput.html";
		
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	}

}
