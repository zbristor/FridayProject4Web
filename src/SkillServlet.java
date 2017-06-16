

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
		String fname = session.getAttribute("FirstName").toString();
		String lname = session.getAttribute("LastName").toString();
		String email = session.getAttribute("Email").toString();
		
		ArrayList<Education> eduArray = (ArrayList<Education>) session.getAttribute("EduArray");
		ArrayList<Work> workArray = (ArrayList<Work>) session.getAttribute("WorkArray");
		ArrayList<Skills> skillArray = (ArrayList<Skills>) session.getAttribute("SkillArray");
		Person p = new Person(fname, lname, email, eduArray, workArray, skillArray);
		Skills s = new Skills(skill);
		p.addSkill(s);
		if(decision.equals("yes"))
		{
			nextURL="/SkillInput.html";
		}
		else if(decision.equals("no"))
		{
			nextURL="/ResumeOutput.jsp";
		}
		

		String bio = fname +" "+lname+"<br>"+email;
		session.setAttribute("Bio", bio);
		String eduStr = "";
		
		for(int i=0; i<eduArray.size(); i++)
		{
			String degree = eduArray.get(i).getDegree();
			String school = eduArray.get(i).getSchool();
			String year = eduArray.get(i).getYear();
			String comb = degree+"<br>"+school+", "+year+"<br>";
			eduStr=eduStr+comb+"<br>";
		}
		session.setAttribute("eduStr", eduStr);
		
		String wStr ="";
		for(int j=0; j<workArray.size(); j++)
		{
			String position = workArray.get(j).getPosition();
			String company= workArray.get(j).getCompany();
			String sDate = workArray.get(j).getStart();
			String eDate = workArray.get(j).getEnd();
			String duties = workArray.get(j).getDuties();
			String wcomb = position+"<br>"+company+", "+sDate+"-"+eDate+"<br>"+duties+"<br>";
			wStr=wStr+wcomb+"<br>";
		}
		session.setAttribute("wStr", wStr);
		String sStr="";
		for(int k=0; k<skillArray.size(); k++)
		{
			String skil = skillArray.get(k).getSkill();
			String scomb = skil;
			sStr=sStr+scomb+"<br>";
		}
		session.setAttribute("sStr", sStr);
		
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt=null;
		//String norm = null;
		String sql = "insert into Skills(SkillName)values(?)";
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resume?"+ "user=root&password=password");
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, skill);
            pstmt.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				getServletContext().getRequestDispatcher(nextURL).forward(request, response);
				rs.close();
				pstmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		//getServletContext().getRequestDispatcher(nextURL).forward(request, response);
		

		

		
		
		
	}

}
