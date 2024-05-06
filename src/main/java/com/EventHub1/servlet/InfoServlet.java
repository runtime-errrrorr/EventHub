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
 * Servlet implementation class Info
 */
@WebServlet("/InfoServlet")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Ename = request.getParameter("ename");
		System.out.println(Ename);
		String Edesc = request.getParameter("edesc");
		String Evenue = request.getParameter("evenue");
	    String eventDate = request.getParameter("edate");
	    String eventTime = request.getParameter("etime");
	    
	    HttpSession session = request.getSession();
	    String username = (String) session.getAttribute("uname");
        RequestDispatcher dispatcher = null;
	    int userID=0;
	    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EventHub?useSSL=false","root","6015@Hashim");
            
            String sql = "SELECT UserID FROM Users WHERE username = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                           // Retrieve the UserID from the result set
                           userID = rs.getInt("UserID");
                           // Use the userID as needed
                           
                       } else {
                           // Handle case where username was not found
                           System.out.println("Username not found.");
                       }

            PreparedStatement pst = con.prepareStatement("insert into Events(EventName,Description,Date,Time,Location,OrganizerID) values (?,?,?,?,?,?) ");

            pst.setString(1,Ename);
            pst.setString(2, Edesc);
            pst.setString(3, eventDate);
            pst.setString(4,eventTime);
            pst.setString(5,Evenue);
            pst.setInt(6,userID);
            
            int rowCount = pst.executeUpdate();
            dispatcher = request.getRequestDispatcher("admin.jsp");
            if (rowCount > 0)
            {
                request.setAttribute("status", "success");

            }
            else
            {
                request.setAttribute("status", "failed");
            }
            dispatcher.forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
            
        }
	}

}
