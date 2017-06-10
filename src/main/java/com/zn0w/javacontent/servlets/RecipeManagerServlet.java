package com.zn0w.javacontent.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.javacontent.dao.Model;
import com.zn0w.javacontent.domain.Recipe;
import com.zn0w.javacontent.domain.User;

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
		
		Model model = new Model();
		
		if (login.equals("Guest"))
			response.sendRedirect("login.jsp");
		else {
			System.out.println("User: " + login + "    Recipe name: " + recipeName + "    Author name: " + author + "    Command: " + command);
			
			Recipe recipe = model.loadRecipe(recipeName, author);
			User user = model.loadUser(login);
			
			if (command.equals("add")) {
				model.addRecipeToFavorites(recipe, user);
			}
			else if (command.equals("remove")) {
				model.removeRecipeFromFavorites(recipe, user);
			}
			else if (command.equals("destroy")) {
				model.deleteRecipe(recipe);
			}
			
			response.sendRedirect("recipesMain.jsp");
		}
	}

}
