package com.zn0w.recipesharing.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.recipesharing.dao.RecipeDao;
import com.zn0w.recipesharing.dao.RecipeDaoImpl;
import com.zn0w.recipesharing.dao.UserDao;
import com.zn0w.recipesharing.dao.UserDaoImpl;
import com.zn0w.recipesharing.domain.Recipe;
import com.zn0w.recipesharing.domain.User;

/**
 * Servlet implementation class FavoritesManagerServlet
 */
public class RecipeManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeManagerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("userLogin");
		String recipeName = request.getParameter("recipeName");
		String author = request.getParameter("author");
		String command = request.getParameter("command");
		
		RecipeDao recipeModel = new RecipeDaoImpl();
		UserDao userModel = new UserDaoImpl();
		
		if (login.equals("Guest"))
			response.sendRedirect("login.jsp");
		else {
			System.out.println("User: " + login + "    Recipe name: " + recipeName + "    Author name: " + author + "    Command: " + command);
			
			Recipe recipe = recipeModel.loadRecipe(recipeName, author);
			User user = userModel.loadUser(login);
			
			if (command.equals("add")) {
				recipeModel.addRecipeToFavorites(recipe, user);
			}
			else if (command.equals("remove")) {
				recipeModel.removeRecipeFromFavorites(recipe, user);
			}
			else if (command.equals("destroy")) {
				recipeModel.deleteRecipe(recipe);
			}
			
			response.sendRedirect("recipesMain.jsp");
		}
	}

}
