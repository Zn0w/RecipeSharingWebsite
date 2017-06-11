package com.zn0w.javacontent.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.javacontent.dao.UserModel;
import com.zn0w.javacontent.domain.Recipe;
import com.zn0w.javacontent.domain.User;

/**
 * Servlet implementation class MyRecipesServlet
 */
public class UserRecipesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRecipesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = null;
		
		UserModel userModel = new UserModel();
		
		boolean isOwner;
		
		if (request.getParameterMap().containsKey("login")) {
			login = request.getParameter("login");
			
			isOwner = false;
		}
		else {
			Cookie cookies[] = request.getCookies();
			
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("username")) {
						login = cookies[i].getValue();
						break;
					}
				}
			}
			
			isOwner = true;
		}
		
		if (login != null) {
			User user = userModel.loadUser(login);
			
			ArrayList<Recipe> userRecipes = user.getUserRecipes();
			
			System.out.println("Test.");
			
			if (userRecipes == null)
				System.out.println("User recipes array is null.");
			
			for (int i = 0; i < userRecipes.size(); i++) {
				System.out.println(userRecipes.get(i).getName());
			}
			
			String[] recipeNames = new String[userRecipes.size()];
			
			for (int i = 0; i < userRecipes.size(); i++) {
				recipeNames[i] = userRecipes.get(i).getName();
			}
			
			request.setAttribute("recipeNames", recipeNames);
			request.setAttribute("userLogin", login);
			
			if (isOwner) {
				request.getRequestDispatcher("myRecipes.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("profile.jsp").forward(request, response);
			}
		}
		else {
			if (isOwner)
				response.sendRedirect("login.jsp");
		}
	}

}
