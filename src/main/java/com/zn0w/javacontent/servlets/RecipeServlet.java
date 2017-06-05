package com.zn0w.javacontent.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.javacontent.model.Model;
import com.zn0w.javacontent.model.Recipe;
import com.zn0w.javacontent.model.user.User;

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
		
		// Favorites handling
		Cookie cookies[] = request.getCookies();
		
		String login = null;
		boolean recipeIsFavourited = false;
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("username")) {
					login = cookies[i].getValue();
				}
			}
		}
		
		if (login != null) {
			User user = model.loadUserFull(login);
			
			ArrayList<Recipe> recipes = user.getFavouritedRecipes();
			
			for (int i = 0; i < recipes.size(); i++) {
				if (recipes.get(i).getID().equals(preferedRecipe.getID())) {
					recipeIsFavourited = true;
					break;
				}
			}
		}
		
		String favoritesStatus = null;
		
		if (recipeIsFavourited)
			favoritesStatus = "favourited";
		else if (preferedRecipe.getAuthorLogin().equals(login))
			favoritesStatus = "owner";
		else
			favoritesStatus = "not favourited";
		
		System.out.println(favoritesStatus);
		
		request.setAttribute("favoritesStatus", favoritesStatus);
		
		request.setAttribute("recipeName", recipeName);
		request.setAttribute("author", recipeAuthor);
		request.setAttribute("ingredients", ingredients);
		request.setAttribute("description", description);
		
		request.getRequestDispatcher("recipe.jsp").forward(request, response);
	}

}
