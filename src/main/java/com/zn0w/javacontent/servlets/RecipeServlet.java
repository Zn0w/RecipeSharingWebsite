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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		model = new Model();
		
		String bn = request.getParameter("button name");
		
		String recipeName = getRecipeName(bn);
		String recipeAuthor = getRecipeAuthor(bn);
		
		System.out.println(recipeName + "|" + recipeAuthor);
		
		String[] ingredients = null;
		String description = "";
		
		Recipe preferedRecipe = model.loadRecipe(recipeName, recipeAuthor);
		if (preferedRecipe != null) {
			ingredients = preferedRecipe.getIngredientsArray();
			description = preferedRecipe.getDescription();
		}
		else {
			System.out.println("ERROR: recipe object is null");
		}
		
		String login = getLogin(request, response);
		
		String relationshipStatus = null;
		if (login == null) {
			relationshipStatus = "not favourited";
		}
		else if (login != null && preferedRecipe != null) {
			relationshipStatus = getRelationshipStatus(login, preferedRecipe);
		}
		
		request.setAttribute("relationshipStatus", relationshipStatus);
		
		request.setAttribute("recipeName", recipeName);
		request.setAttribute("author", recipeAuthor);
		request.setAttribute("ingredients", ingredients);
		request.setAttribute("description", description);
		
		request.getRequestDispatcher("recipe.jsp").forward(request, response);
	}
	
	private String getRecipeName(String sourceText) {
		String recipeName = "";
		
		for (int i = 0; i < sourceText.length(); i++) {
			if (sourceText.charAt(i) == ' ' && sourceText.charAt(i + 1) == 'b' && sourceText.charAt(i + 2) == 'y' && sourceText.charAt(i + 3) == ' ') {
				break;
			}
			
			recipeName += sourceText.charAt(i);
		}
		
		return recipeName;
	}
	
	private String getRecipeAuthor(String sourceText) {
		String recipeAuthor = "";
		
		boolean startPointFound = false;
		
		for (int i = 0; i < sourceText.length(); i++) {
			if (i > 3 && sourceText.charAt(i - 3) == ' ' && sourceText.charAt(i - 2) == 'b' && sourceText.charAt(i - 1) == 'y' && sourceText.charAt(i) == ' ') {
				startPointFound = true;
				continue;
			}
			
			if (startPointFound) {
				recipeAuthor += sourceText.charAt(i);
			}
		}
		
		return recipeAuthor;
	}
	
	private void loadRecipeInfo(Recipe recipe) {
		if (recipe != null) {
			String[] ingredients = recipe.getIngredientsArray();
			String description = recipe.getDescription();
		}
		else {
			System.out.println("ERROR: recipe object is null");
		}
	}
	
	private String getRelationshipStatus(String login, Recipe recipe) {
		String relationshipStatus = null;
		
		boolean recipeIsFavourited = false;
		
		User user = model.loadUser(login);
		
		ArrayList<Recipe> recipes = user.getFavouritedRecipes();
		
		for (int i = 0; i < recipes.size(); i++) {
			if (recipes.get(i).getID() == recipe.getID()) {
				recipeIsFavourited = true;
				break;
			}
		}	
		
		if (recipeIsFavourited)
			relationshipStatus = "favourited";
		else if (recipe.getAuthorLogin().equals(login))
			relationshipStatus = "owner";
		else
			relationshipStatus = "not favourited";
		
		System.out.println(relationshipStatus);
		
		return relationshipStatus;
	}
	
	private String getLogin(HttpServletRequest request, HttpServletResponse response) {
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
		
		return login;
	}

}