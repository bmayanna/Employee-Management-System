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

import entity.EmpEntity;
import service.EmpService;

/**
 * Servlet implementation class InactiveUsersController
 */
@WebServlet("/InactiveUsersController")
public class InactiveUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InactiveUsersController() {
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
		List<EmpEntity> info = new ArrayList<EmpEntity>();
		List<EmpEntity> Minfo = new ArrayList<EmpEntity>();
		int id = (int)session.getAttribute("userid");
		String data = Integer.toString(id);
		System.out.println("Id - "+id);
		try {
			info = EmpService.inactiveuser(data);
			request.setAttribute("info", info);
			Minfo = EmpService.managerlist(data);
			request.setAttribute("Minfo", Minfo);
			RequestDispatcher rs = request.getRequestDispatcher("Inactiveusers.jsp");
		    rs.include(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	}

}
