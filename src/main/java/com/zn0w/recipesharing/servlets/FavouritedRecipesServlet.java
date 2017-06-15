package com.zn0w.recipesharing.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.recipesharing.dao.UserDaoImpl;
import com.zn0w.recipesharing.domain.Recipe;
import com.zn0w.recipesharing.domain.User;

/**
 * Servlet implementation class FavouritedRecipesServlet
 */
public class FavouritedRecipesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavouritedRecipesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDaoImpl userModel = new UserDaoImpl();
		
		Cookie cookies[] = request.getCookies();
		
		String login = null;
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("username")) {
					login = cookies[i].getValue();
				}
			}
		}
		
		if (login != null) {
			User user = userModel.loadUser(login);			
			ArrayList<Recipe> favouritedRecipes = user.getFavouritedRecipes();
			
			String[][] recipeNames = new String[favouritedRecipes.size()][2];
			
			for (int i = 0; i < favouritedRecipes.size(); i++) {
				recipeNames[i][0] = favouritedRecipes.get(i).getName();
				recipeNames[i][1] = favouritedRecipes.get(i).getAuthorLogin();
			}
			
			request.setAttribute("recipeNames", recipeNames);
			request.getRequestDispatcher("favouritedRecipes.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
