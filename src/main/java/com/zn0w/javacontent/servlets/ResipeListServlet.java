package com.zn0w.javacontent.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.javacontent.model.Model;
import com.zn0w.javacontent.model.Recipe;

/**
 * Servlet implementation class ResipesServlet
 */
public class ResipeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Model model;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResipeListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		model = new Model();
		ArrayList<Recipe> recipes = model.getRecipes();
		
		String[][] recipeInfo = new String[recipes.size()][4];
		
		for (int i = 0; i < recipes.size(); i++) {
			Recipe recipe = recipes.get(i);
			
			recipeInfo[i][0] = recipe.getName();
			recipeInfo[i][1] = recipe.getIngredients();
			recipeInfo[i][2] = recipe.getDescription();
			recipeInfo[i][3] = recipe.getAuthorName();
		}
		
		request.setAttribute("recipes", recipeInfo);
		request.getRequestDispatcher("recipeList.jsp").forward(request, response);
		
		request.getRequestDispatcher("RecipeServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
