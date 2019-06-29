package controllers;

import java.io.IOException;
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
import entity.RequestLeave;

/**
 * Servlet implementation class RequestLeaveforManager
 */
@WebServlet("/RequestLeaveforManager")
public class RequestLeaveforManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestLeaveforManager() {
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
		//doGet(request, response);
		HttpSession session=request.getSession();
		int managerId = (int) session.getAttribute("userid");
		List<RequestLeave> feedlist=new ArrayList<RequestLeave>();
		try {
			feedlist= EmpRegisterDao.RequestLeaveList(managerId);
			request.setAttribute("feedlist", feedlist);
			RequestDispatcher rd= request.getRequestDispatcher("ViewLeaveRequests.jsp");//Employee
			rd.forward(request, response);
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


