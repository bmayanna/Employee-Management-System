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
 * Servlet implementation class ActiveUsersController
 */
@WebServlet("/ActiveUsersController")
public class ActiveUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActiveUsersController() {
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
			int id = (int)session.getAttribute("userid");
			List<EmpEntity> info = new ArrayList<EmpEntity>();
			try {
				info = EmpService.activeuser(id);
				request.setAttribute("info", info);
				RequestDispatcher rs = request.getRequestDispatcher("ActiveUsers.jsp");
			    rs.forward(request, response);
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
		List<EmpEntity> info = new ArrayList<EmpEntity>();
		try
		{
			HttpSession session= request.getSession();
			int id= (Integer)session.getAttribute("userid");
			info = EmpService.activeuser(id);
			request.setAttribute("info", info);
			RequestDispatcher rs = request.getRequestDispatcher("ActiveUsers.jsp");
		    rs.forward(request, response);
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
