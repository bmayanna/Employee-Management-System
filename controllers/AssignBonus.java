package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmpRegisterDao;
import entity.EmpEntity;
import entity.Salary;
import service.EmpService;

/**
 * Servlet implementation class AssignBonus
 */
@WebServlet("/AssignBonus")
public class AssignBonus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignBonus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session.getAttribute("username") != null){
		int id = (int) session.getAttribute("userid");
		try{
			List<EmpEntity> user = new ArrayList<EmpEntity>();
			user= EmpRegisterDao.getSubUsers(id);
			List<Salary> bonus = new ArrayList<Salary>();
			bonus = EmpService.getbonus(id,user);
			request.setAttribute("bonus", bonus);
			RequestDispatcher rd= request.getRequestDispatcher("AssignBonus.jsp");
			rd.include(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		}else{
			session.invalidate(); 
		    response.sendRedirect("EmployeeLogin.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int mid = (int) session.getAttribute("userid");
		String salaryid = request.getParameter("salaryid");
		String empid = request.getParameter("empid");
		String bonus = request.getParameter("bonus");
		try {
			Salary sal = EmpService.updateBonus(salaryid,empid,bonus);
			PrintWriter out = response.getWriter();
			out.println("<font color=green>Bonus updated successfully</font>");
			RequestDispatcher rd= request.getRequestDispatcher("ManagerHome.jsp");
			rd.include(request, response);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
