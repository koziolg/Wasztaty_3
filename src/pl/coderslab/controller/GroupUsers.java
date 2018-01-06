package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Group;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

/**
 * Servlet implementation class GroupUsers
 */
@WebServlet("/GroupUsers")
public class GroupUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			Connection conn = DbUtil.getConn();
			Integer groupId = Integer.parseInt(getInitParameter(("groupId")));
			
			User[] users = User.loadAllByGrupId(conn, groupId);
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/WEB-INF/views/groupUsers.jsp").forward(request, response);
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
