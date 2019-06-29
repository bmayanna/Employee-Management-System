package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import entity.UploadDocument;
import service.EmpService;

/**
 * Servlet implementation class DownloadFilesController
 */
@WebServlet("/DownloadFilesController")
public class DownloadFilesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadFilesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		if(session.getAttribute("username") != null){
		int mid = (int)session.getAttribute("userid");
		try {
			List<UploadDocument> download = EmpService.getDocuments(mid);
			request.setAttribute("download", download);
			RequestDispatcher rd= request.getRequestDispatcher("DownloadDocument.jsp");//Employee
			rd.forward(request, response);
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
		int mid = (int)session.getAttribute("userid");
		try{
			int file = Integer.parseInt(request.getParameter("fileId"));
			UploadDocument docdata = EmpService.getDoc(file);
			request.setAttribute("files", docdata);
			RequestDispatcher rd= request.getRequestDispatcher("download.jsp");
			rd.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
