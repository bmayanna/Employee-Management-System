package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import entity.Directory;
import entity.UploadDocument;
import service.EmpService;

/**
 * Servlet implementation class UploadFileController
 */
@WebServlet("/UploadFileController")
@MultipartConfig(maxFileSize = 16000000)
public class UploadFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileController() {
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
			List<Directory> directory = EmpService.getDir(mid);
			request.setAttribute("directory", directory);
			RequestDispatcher rd= request.getRequestDispatcher("UploadDocument.jsp");//Employee
			rd.include(request, response);
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
			String docname = request.getParameter("document_name");
			int dirid = Integer.parseInt(request.getParameter("directory_list"));
			Part document = request.getPart("file");
			InputStream is = document.getInputStream();
			String mime = document.getContentType();
			UploadDocument doc = EmpService.updateFile(dirid,mid,docname,is,mime);
			request.setAttribute("raj", "Document Uploaded Successfully");
			doGet(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
