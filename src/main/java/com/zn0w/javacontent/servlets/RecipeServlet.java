package com.zn0w.javacontent.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.javacontent.model.Model;
import com.zn0w.javacontent.model.Recipe;

/**
 * Servlet implementation class RecipeServlet
 */
public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Model model;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeServlet() {
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
		
		String bn = request.getParameter("button name");
		
		// Getting name of the recipe
		String recipeName = "";
		boolean nameIsFound = false;
		
		String recipeAuthor = "";
		int authorNameIndex = 0;
		
		for (int i = 0; i < bn.length(); i++) {
			if (nameIsFound) {
				if (authorNameIndex == i) {
					recipeAuthor += bn.charAt(i);
					authorNameIndex += 1;
				}
			}
			
			if (!nameIsFound) {
				if (bn.charAt(i) == ' ' && bn.charAt(i + 1) == 'b' && bn.charAt(i + 2) == 'y' && bn.charAt(i + 3) == ' ') {
					authorNameIndex = i + 4;
					nameIsFound = true;
					continue;
				}
				
				recipeName += bn.charAt(i);
			}
		}
		
		System.out.println(recipeName + recipeAuthor);
		
		// Getting recipe
		Recipe preferedRecipe = model.loadRecipe(recipeName, recipeAuthor);
		
		String[] ingredients = null;
		String description = "";
		
		if (preferedRecipe != null) {
			ingredients = preferedRecipe.getIngredientsArray();
			description = preferedRecipe.getDescription();
		}
		else {
			System.out.println("ERROR: recipe object is null");
		}
		
		System.out.println("description: " + description);
		
		request.setAttribute("recipeName", recipeName);
		request.setAttribute("author", recipeAuthor);
		request.setAttribute("ingredients", ingredients);
		request.setAttribute("description", description);
		
		request.getRequestDispatcher("recipe.jsp").forward(request, response);
	}

}
