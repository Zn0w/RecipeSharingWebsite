package com.zn0w.javacontent.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.javacontent.model.Model;
import com.zn0w.javacontent.model.user.User;

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
		String name = request.getParameter("name");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		String message = "";
		
		if (!password1.equals(password2))
			message = "Passwords don't match.";
		else if ((!model.loginIsCreated(login)) && (!model.nameIsCreated(name))) {
			User user = new User(login, name, password1);
			model.registerUser(user);
			message = "User has been succesfully created!";
		}
		else if (model.loginIsCreated(login))
			message = "Your login already exists.";
		else if (model.nameIsCreated(name))
			message = "Your display name already exists.";
		else
			message = "Something went wrong.";
		
		request.setAttribute("message", message);
		
		request.getRequestDispatcher("registerResult.jsp").forward(request, response);
	}

}
