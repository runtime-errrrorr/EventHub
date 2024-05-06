
package com.EventHub1.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        String erole = request.getParameter("role");
        System.out.println(erole);
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        try
        {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EventHub?useSSL=false","root","6015@Hashim");
            PreparedStatement pst = con.prepareStatement("select * from Users where Username = ? and Password = ?");
            
            pst.setString(1, username);
            pst.setString(2, password);
            
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
            	session.setAttribute("uname", username);
            	if("Student".equals(erole))
            	{
            		System.out.println("User role: Student"); 
            		dispatcher = request.getRequestDispatcher("student.jsp");
            	}
            	else
            	{
            		System.out.println("User role: Admin");
            		dispatcher = request.getRequestDispatcher("admin.jsp");
            	}
            }
            else
            {
            	request.setAttribute("status", "failed");
            	dispatcher = request.getRequestDispatcher("index.jsp");
            }
            dispatcher.forward(request, response);
            
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
	}

}
