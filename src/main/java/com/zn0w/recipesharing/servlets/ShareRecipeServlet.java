package com.zn0w.recipesharing.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.recipesharing.dao.RecipeModel;
import com.zn0w.recipesharing.domain.Recipe;

/**
 * Servlet implementation class ShareRecipeServlet
 */
public class ShareRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareRecipeServlet() {
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
		RecipeModel recipeModel = new RecipeModel();
		
		String name = request.getParameter("name");
		String ingredients = request.getParameter("ingredients");
		String description = request.getParameter("description");
		String user = request.getParameter("user");
		
		String message = null;
		
		if (name.equals("") || description.equals(""))
			message = "You mustn't leave name or description field blank.";
		else if (!recipeModel.recipeWithNameExists(request.getParameter("name"), request.getParameter("user"))) {
			Recipe recipe = new Recipe();
			
			recipe.setName(request.getParameter("name"));
			recipe.setIngredients(request.getParameter("ingredients"));
			recipe.setDescription(request.getParameter("description"));
			recipe.setAuthorLogin(request.getParameter("user"));
			
			recipeModel.saveNewRecipe(recipe);
			
			message = "Recipe has been successfully shared.";
		}
		else
			message = "You have already shared recipe with this name. Try another one.";
		
		request.setAttribute("message", message);
		
		request.getRequestDispatcher("shareRecipeResult.jsp").forward(request, response);
	}

}
