package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmpRegisterDao;

/**
 * Servlet implementation class RequestLeaveController
 */
@WebServlet("/RequestLeaveController")
public class RequestLeaveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestLeaveController() {
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
			RequestDispatcher rd= request.getRequestDispatcher("RequestLeave.jsp");//Employee
			rd.include(request, response);
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
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		int id =(int)session.getAttribute("userid");
		int mid = (int) session.getAttribute("managerid");
		String details= request.getParameter("details");
	    try {
			Boolean status = EmpRegisterDao.saveLeave(id,details,mid);
			request.setAttribute("raj", "Success");
			RequestDispatcher rd= request.getRequestDispatcher("RequestLeave.jsp");//Employee
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
