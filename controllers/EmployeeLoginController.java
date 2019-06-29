package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import dao.EmpRegisterDao;
import entity.EmpEntity;


/**
 * Servlet implementation class EmployeeLoginController
 */
@SuppressWarnings("unused")
@WebServlet("/EmployeeLoginController")
public class EmployeeLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			if(session.getAttribute("username") != null){
				int id = (int)session.getAttribute("userid");
				EmpEntity raj;
				try {
					raj = EmpRegisterDao.getData(id);
					if(raj.getLevel() == 1){
						//request.setAttribute("raj", raj);
						RequestDispatcher rd= request.getRequestDispatcher("EmployeeHome.jsp");//Employee
						rd.include(request, response);
					}else if(raj.getLevel() == 2){
						//request.setAttribute("raj", raj);
						RequestDispatcher rd= request.getRequestDispatcher("ManagerHome.jsp");//Manager
						rd.include(request, response);
					}else if(raj.getLevel() == 3){
						//request.setAttribute("raj", raj);
						RequestDispatcher rd= request.getRequestDispatcher("AdminHome.jsp");//Admin
						rd.include(request, response);
					}
					else{
						PrintWriter out = response.getWriter();
						out.println("<font color=red>Please Register first, You are not registered in our system</font>");
						RequestDispatcher rd = request.getRequestDispatcher("EmployeeRegisteration.jsp");
						rd.include(request, response);
					}
				} catch (SQLException e) {
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
			//session.setAttribute()
			
			String username= request.getParameter("username");
			String password= request.getParameter("password");
			List<EmpEntity> empinfo = new ArrayList<EmpEntity>();
			List<EmpEntity> manginfo = new ArrayList<EmpEntity>();
			try{
				EmpEntity raj = EmpRegisterDao.validate(username,password);
				request.setAttribute("Id", raj.getId());
				session.setAttribute("userid", raj.getId());
				session.setAttribute("roleid", raj.getLevel());
				session.setAttribute("managerid", raj.getManager_id());
				session.setAttribute("username", raj.getUsername());
				//request.setAttribute()
				if(username.isEmpty()||password.isEmpty())
				{
					PrintWriter out = response.getWriter();
					out.println("<font color=red>Please fill all the fields, all the fields are mandatory</font>");
					RequestDispatcher rd = request.getRequestDispatcher("EmployeeLogin.jsp");
					rd.include(request, response);
				}
				else{
				if(raj.getStatus()){
					if(raj.getLevel() == 1){
						//request.setAttribute("raj", raj);
						RequestDispatcher rd= request.getRequestDispatcher("EmployeeHome.jsp");//Employee
						rd.include(request, response);
					}else if(raj.getLevel() == 2){
						//request.setAttribute("raj", raj);
						RequestDispatcher rd= request.getRequestDispatcher("ManagerHome.jsp");//Manager
						rd.include(request, response);
					}else if(raj.getLevel() == 3){
						//request.setAttribute("raj", raj);
						RequestDispatcher rd= request.getRequestDispatcher("AdminHome.jsp");//Admin
						rd.include(request, response);
					}
					else{
						PrintWriter out = response.getWriter();
						out.println("<font color=red>Please Register first, You are not registered in our system</font>");
						RequestDispatcher rd = request.getRequestDispatcher("EmployeeRegisteration.jsp");
						rd.include(request, response);
					}
				}
				else{  
					request.setAttribute("error", "Invalid");  
			        RequestDispatcher rd=request.getRequestDispatcher("EmployeeLogin.jsp");  
			        rd.include(request,response);  
			    }
			}
			}
			catch(Exception e){
			     e.printStackTrace();
			}
		}  

}
