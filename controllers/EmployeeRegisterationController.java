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

import dao.EmpRegisterDao;
import dao.LeaveRequestDao;
import dao.SalaryDao;
import entity.EmpEntity;
import entity.LeaveEntity;
import entity.Salary;

/**
 * Servlet implementation class EmployeeRegisterationController
 */
@WebServlet("/EmployeeRegisterationController")
public class EmployeeRegisterationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeRegisterationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("EmployeeRegisteration.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String f_name=request.getParameter("f_name");
		String l_name=request.getParameter("l_name");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if(username.isEmpty()||f_name.isEmpty()||l_name.isEmpty()||password.isEmpty()){
			out.println("<font color=red>Please fill all the fields, all the fields are mandatory</font>");
			RequestDispatcher rd = request.getRequestDispatcher("EmployeeRegisteration.jsp");
			rd.include(request, response);
		}
		else{
			try {
				EmpEntity empentity = EmpRegisterDao.register(f_name,l_name,email,address,phone,username,password);
				if(empentity.getFunctionResponse()) //On success, you can display a message to user on Home page
				{
					LeaveEntity leave= LeaveRequestDao.leave(empentity.getEmp_id());
					Salary salary =SalaryDao.updatesalary(empentity.getEmp_id());
					request.setAttribute("congo", "Congratulations!! You are successfully registered.");
					RequestDispatcher rd=request.getRequestDispatcher("EmployeeLogin.jsp");
					rd.forward(request,response);
				}
				else //On Failure, display a meaningful message to the User.
				{
					request.setAttribute("error", "Registration Failed");
					RequestDispatcher rd = request.getRequestDispatcher("EmployeeRegisteration.jsp");
					rd.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


			

}

		
	


