

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		
		String firstName = session.getAttribute("FirstName").toString();
		String lastName = session.getAttribute("LastName").toString();
		String email = session.getAttribute("Email").toString();
		ArrayList<Education> eduArray = (ArrayList<Education>) session.getAttribute("EduArray");
		ArrayList<Work> workArray = (ArrayList<Work>) session.getAttribute("WorkArray");
		ArrayList<Skills> skillArray = (ArrayList<Skills>) session.getAttribute("SkillArray");
		Person p = new Person(firstName, lastName, email, eduArray, workArray, skillArray);
		Work w = new Work(position, company, sDate, eDate, duties);
		p.addWork(w);
		if(decision.equals("yes"))
		{
			nextURL="/WorkInput.html";
		}
		else if(decision.equals("no"))
		{
			nextURL="/SkillInput.html";
		}
		
		Connection con = null;
		PreparedStatement pstmt=null;
		String sql = "insert into Work(Position,Company,StartTime,EndTime,Duty1)values(?,?,?,?,?)";
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resume?"+ "user=root&password=password");
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, position);
            pstmt.setString(2, company);
            pstmt.setString(3, sDate);
            pstmt.setString(4, eDate);
            pstmt.setString(5, duties);
            pstmt.executeUpdate();
		
	
		}catch (SQLException f){
			f.printStackTrace();
		} catch (ClassNotFoundException f) {
			f.printStackTrace();
		}
		finally {
			try {
				getServletContext().getRequestDispatcher(nextURL).forward(request, response);
				pstmt.close();
				con.close();
			}catch(SQLException f){
				f.printStackTrace();
			}
		}
		//getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	}

}
