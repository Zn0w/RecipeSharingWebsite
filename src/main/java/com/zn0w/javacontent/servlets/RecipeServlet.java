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
	
	private Model model = new Model();
	private String recipeName = "";
	private String recipeAuthor = "";
	private String[] ingredients = null;
	private String description = "";
       
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
		getRecipeNameAndAuthor(bn);
		
		Recipe preferedRecipe = model.loadRecipe(recipeName, recipeAuthor);
		loadRecipeInfo(preferedRecipe);
		
		String relationshipStatus = getRelationshipStatus(request, response, preferedRecipe);
		
		
		request.setAttribute("relationshipStatus", relationshipStatus);
		
		request.setAttribute("recipeName", recipeName);
		request.setAttribute("author", recipeAuthor);
		request.setAttribute("ingredients", ingredients);
		request.setAttribute("description", description);
		
		request.getRequestDispatcher("recipe.jsp").forward(request, response);
	}
	
	private void getRecipeNameAndAuthor(String sourceText) {
		boolean nameIsFound = false;
		
		int authorNameIndex = 0;
		
		for (int i = 0; i < sourceText.length(); i++) {
			if (nameIsFound) {
				if (authorNameIndex == i) {
					recipeAuthor += sourceText.charAt(i);
					authorNameIndex += 1;
				}
			}
			
			if (!nameIsFound) {
				if (sourceText.charAt(i) == ' ' && sourceText.charAt(i + 1) == 'b' && sourceText.charAt(i + 2) == 'y' && sourceText.charAt(i + 3) == ' ') {
					authorNameIndex = i + 4;
					nameIsFound = true;
					continue;
				}
				
				recipeName += sourceText.charAt(i);
			}
		}
		
		System.out.println(recipeName + recipeAuthor);
	}
	
	private void loadRecipeInfo(Recipe recipe) {
		if (recipe != null) {
			ingredients = recipe.getIngredientsArray();
			description = recipe.getDescription();
		}
		else {
			System.out.println("ERROR: recipe object is null");
		}
		
		System.out.println("description: " + description);
	}
	
	private String getRelationshipStatus(HttpServletRequest request, HttpServletResponse response, Recipe recipe) {
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
			User user = model.loadUser(login);
			
			ArrayList<Recipe> recipes = user.getFavouritedRecipes();
			
			for (int i = 0; i < recipes.size(); i++) {
				if (recipes.get(i).getID() == recipe.getID()) {
					recipeIsFavourited = true;
					break;
				}
			}
		}
		
		String relationshipStatus = null;
		
		if (recipeIsFavourited)
			relationshipStatus = "favourited";
		else if (recipe.getAuthorLogin().equals(login))
			relationshipStatus = "owner";
		else
			relationshipStatus = "not favourited";
		
		System.out.println(relationshipStatus);
		
		return relationshipStatus;
	}

}
