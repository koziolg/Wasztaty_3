package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

/**
 * Servlet implementation class UserByID
 */
@WebServlet("/UserByID")
public class UserByID extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			Connection conn = DbUtil.getConn();
			Integer userID = Integer.parseInt(getInitParameter(("userID")));
			
			User user = User.loadUserById(conn, userID);
			request.setAttribute("user", user);
			getServletContext().getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
