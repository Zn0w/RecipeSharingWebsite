package com.zn0w.recipesharing.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.recipesharing.dao.UserDao;
import com.zn0w.recipesharing.dao.UserDaoImpl;
import com.zn0w.recipesharing.domain.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userModel = new UserDaoImpl();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User preferedUser = null;
		
		if (userModel.loginIsCreated(username)) {
			preferedUser = userModel.loadUser(username);
		}
		else {
				System.out.println("User with this username doesn't exist.");
				
				String message = "User with this username doesn't exist.";
				request.setAttribute("message", message);
				request.getRequestDispatcher("loginFailed.jsp").forward(request, response);
			}
		
		if (preferedUser != null) {
			System.out.println("Success.");
			
			if (preferedUser.getPassword().equals(password)) {
				Cookie cookie = new Cookie("username", username);
				
				response.addCookie(cookie);
				
				response.sendRedirect("index.jsp");
			}
			else {
				String message = "Password is incorrect.";
				request.setAttribute("message", message);
				request.getRequestDispatcher("loginFailed.jsp").forward(request, response);
			}
		}
	}

}
