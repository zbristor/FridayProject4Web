

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BioServlet")
public class BioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BioServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextURL = "/EducationInput.html";
		HttpSession session = request.getSession();
		
		Connection con = null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		
		ArrayList<Education> eduArray = new ArrayList<Education>();
		session.setAttribute("EduArray", eduArray);
		ArrayList<Work> workArray = new ArrayList<Work>();
		session.setAttribute("WorkArray", workArray);
		ArrayList<Skills> skillArray = new ArrayList<Skills>();
		session.setAttribute("SkillArray", skillArray);
		
		String firstName = request.getParameter("FirstName");
		session.setAttribute("FirstName", firstName);
		String lastName = request.getParameter("LastName");
		session.setAttribute("LastName", lastName);
		String email = request.getParameter("Email");
		session.setAttribute("Email", email);
		Person p = new Person(firstName, lastName, email, eduArray, workArray, skillArray);

		String sql = "insert into Person(FirstName,LastName,Email)values(?,?,?)";
		String sql2 = "select PersonID from Person where Email=? and FirstName=? and LastName=?";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resume?"+ "user=root&password=password");
           pstmt = con.prepareStatement(sql);
           pstmt.setString(1, firstName);
           pstmt.setString(2, lastName);
           pstmt.setString(3, email);
           pstmt.executeUpdate();
	
            //pstmt2=con.prepareStatement(sql2);
            pstmt2 = con.prepareStatement(sql2);
            pstmt2.setString(1, session.getAttribute("Email").toString());
            pstmt2.setString(2, firstName);
            pstmt2.setString(3, lastName);
            ResultSet s = pstmt2.executeQuery();
            s.next();
            String pID = s.getString("PersonID");
            int parseID = Integer.parseInt(pID);
            session.setAttribute("personID", parseID);
            
            

            
		}catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				getServletContext().getRequestDispatcher(nextURL).forward(request, response);
				pstmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		//getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	}

}
