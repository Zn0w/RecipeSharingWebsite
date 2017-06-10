package com.zn0w.javacontent.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.javacontent.dao.Model;
import com.zn0w.javacontent.domain.Recipe;
import com.zn0w.javacontent.domain.User;

/**
 * Servlet implementation class MyRecipesServlet
 */
public class MyRecipesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Model model;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyRecipesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		model = new Model();
		
		Cookie cookies[] = request.getCookies();
		
		String login = null;
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("username")) {
					login = cookies[i].getValue();
					break;
				}
			}
		}
		
		if (login != null) {
			User user = model.loadUser(login);
			
			ArrayList<Recipe> myRecipes = user.getUserRecipes();
			
			System.out.println("Test.");
			
			if (myRecipes == null)
				System.out.println("User recipes array is null.");
			
			for (int i = 0; i < myRecipes.size(); i++) {
				System.out.println(myRecipes.get(i).getName());
			}
			
			String[] recipeNames = new String[myRecipes.size()];
			
			for (int i = 0; i < myRecipes.size(); i++) {
				recipeNames[i] = myRecipes.get(i).getName();
			}
			
			request.setAttribute("recipeNames", recipeNames);
			request.getRequestDispatcher("myRecipes.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
