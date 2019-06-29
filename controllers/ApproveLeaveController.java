package controllers;

import java.io.IOException;
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
import dao.LeaveRequestDao;
import entity.RequestLeave;

/**
 * Servlet implementation class ApproveLeaveController
 */
@WebServlet("/ApproveLeaveController")
public class ApproveLeaveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveLeaveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		session.invalidate(); 
	    response.sendRedirect("EmployeeLogin.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int managerId = (int) session.getAttribute("managerid");
		String res=request.getParameter("response");
		String eid=request.getParameter("employeeId");
		String mid=request.getParameter("managerId");
		String lid=request.getParameter("leaveId");
		int a=Integer.parseInt(mid);
		int b=Integer.parseInt(eid);
		int c=Integer.parseInt(lid);
		try{
			RequestLeave requestleave= LeaveRequestDao.resleave(res,a,b,c);
			if(requestleave.getStatus()){
				List<RequestLeave> feedlist=new ArrayList<RequestLeave>();
				feedlist= EmpRegisterDao.RequestLeaveList(managerId);
				request.setAttribute("feedlist", feedlist);
				RequestDispatcher rd= request.getRequestDispatcher("ViewLeaveRequests.jsp");//Employee
				rd.forward(request, response);
			}
			else{
				List<RequestLeave> feedlist=new ArrayList<RequestLeave>();
				feedlist= EmpRegisterDao.RequestLeaveList(managerId);
				request.setAttribute("feedlist", feedlist);
				RequestDispatcher rd= request.getRequestDispatcher("ViewLeaveRequests.jsp");//Employee
				rd.forward(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
