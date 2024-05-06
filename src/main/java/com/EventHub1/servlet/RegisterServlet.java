package com.EventHub1.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

            String uname = request.getParameter("username");
            String upass = request.getParameter("password");
            String uroll = request.getParameter("role");

            RequestDispatcher dispatcher = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EventHub?useSSL=false","root","6015@Hashim");

                PreparedStatement pst = con.prepareStatement("insert into Users(Username,Password,UserType) values (?,?,?) ");

                pst.setString(1,uname);
                pst.setString(2, upass);
                pst.setString(3, uroll);

                int rowCount = pst.executeUpdate();
                dispatcher = request.getRequestDispatcher("index.jsp");
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
