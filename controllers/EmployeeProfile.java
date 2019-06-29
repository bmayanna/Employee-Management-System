package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmpRegisterDao;
import entity.EmpEntity;

import utility.DBConnection;

/**
 * Servlet implementation class EmployeeProfile
 */
@WebServlet("/EmployeeProfile")
public class EmployeeProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession session= request.getSession();
		if(session.getAttribute("username") != null){
		int id = (int)session.getAttribute("userid");
		EmpEntity regdata=new EmpEntity();
		try {
			regdata= EmpRegisterDao.getData(id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if(regdata != null){
			    edit(regdata,request).forward(request, response);
			}else{
				out.println("Please Login First");
				RequestDispatcher rd = request.getRequestDispatcher("EmployeeLogin.jsp");
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();//System.out.println("Profile do get2"+e);
		}
		}else{
			session.invalidate(); 
		    response.sendRedirect("EmployeeLogin.jsp");
		}
	}
	
	protected RequestDispatcher edit(EmpEntity regdata,HttpServletRequest request){
		request.setAttribute("f_name", regdata.getF_name());
		request.setAttribute("l_name", regdata.getL_name());
		request.setAttribute("address", regdata.getAddress());
		request.setAttribute("email", regdata.getEmail());
		request.setAttribute("phone", regdata.getPhone());
		RequestDispatcher rs = request.getRequestDispatcher("proffile.jsp");
		return rs;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int id= (int)session.getAttribute("userid");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		try {
			Boolean reponse = EmpRegisterDao.updateData(id,address,phone,email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
