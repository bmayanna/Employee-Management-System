package controllers;
	
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.EmpEntity;
import service.EmpService;

/**
 * Servlet implementation class SaveUsers
 */
@WebServlet("/SaveUsers")
public class SaveUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.invalidate(); 
	    response.sendRedirect("EmployeeLogin.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("employeeId");
		String manager_id = request.getParameter("assign");
		String level = request.getParameter("level");
		String designation = request.getParameter("designation");
		String division = request.getParameter("division");
		//System.out.println("Incoming");
		int result = Integer.parseInt(id);
		int result1 = Integer.parseInt(manager_id);
		int result2 = Integer.parseInt(level);
		
		try{
			EmpEntity regdata = EmpService.saveUser(result,result1,result2,designation,division);
			if(regdata.getStatus()){
				List<EmpEntity> info  = EmpService.inactiveuser(id);
				List<EmpEntity> managerinfo = EmpService.managerlist(id);
				request.setAttribute("info", info);
				request.setAttribute("Minfo", managerinfo);
				RequestDispatcher rs = request.getRequestDispatcher("Inactiveusers.jsp");
			    rs.include(request, response);
			}
			else{
				List<EmpEntity> info  = EmpService.inactiveuser(id);
				List<EmpEntity> managerinfo = EmpService.managerlist(id);
				request.setAttribute("info", info);
				request.setAttribute("Minfo", managerinfo);
				RequestDispatcher rd = request.getRequestDispatcher("Inactiveusers.jsp");
				rd.include(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}


