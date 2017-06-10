package com.zn0w.javacontent.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.javacontent.dao.Model;
import com.zn0w.javacontent.domain.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Model model;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		
		String login = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		String message = "";
		
		if (login.endsWith(" ") || password1.endsWith(" ")) {
			message = "Do not put spaces after symbols!";
		}
		else if (!password1.equals(password2))
			message = "Passwords don't match.";
		else if ((!model.loginIsCreated(login))) {
			User user = new User(login, password1);
			model.registerUser(user);
			message = "User has been succesfully created!";
		}
		else if (model.loginIsCreated(login))
			message = "Your login already exists.";
		else
			message = "Something went wrong.";
		
		request.setAttribute("message", message);
		
		request.getRequestDispatcher("registerResult.jsp").forward(request, response);
	}

}
