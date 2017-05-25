package com.zn0w.javacontent.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.javacontent.model.Model;
import com.zn0w.javacontent.model.user.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Model model;
       
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
		model = new Model();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		model.loadMinimumUsersInfo();
		ArrayList<User> users = model.getUsers();
		
		User preferedUser = null;
		
		if (users != null) {
			if (model.loginIsCreated(username)) {
				for (int i = 0; i < users.size(); i++) {
					User user = users.get(i);
					
					System.out.println("user" + i);
					
					if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
						System.out.println("Got user.");
						
						preferedUser = user;
						break;
					}
				}
			}
			else {
				System.out.println("User with this username doesn't exist.");
				
				String message = "User with this username doesn't exist.";
				request.setAttribute("message", message);
				request.getRequestDispatcher("loginFailed.jsp").forward(request, response);
			}
		}
		
		if (preferedUser != null) {
			System.out.println("Success.");
			
			Cookie cookie = new Cookie("username", username);
			//cookie.setMaxAge(60 * 60);
			
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
