package com.zn0w.recipesharing.servlets;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getParameter("typeOfSearch");
		String query = request.getParameter("searchQuery");
		
		if (searchType.equals("recipe")) {
			ArrayList<Recipe> recipes = searchByRecipe(query);
			
			String[][] recipesInfo = new String[recipes.size()][2];
			
			for (int i = 0; i < recipes.size(); i++) {
				Recipe recipe = recipes.get(i);
				
				recipesInfo[i][0] = recipe.getName();
				recipesInfo[i][1] = recipe.getAuthorLogin();
			}
			
			request.setAttribute("recipes", recipesInfo);
			request.setAttribute("searchType", "recipe");
			
			request.getRequestDispatcher("searchResults.jsp").forward(request, response);
		}
		else if (searchType.equals("user")) {
			ArrayList<User> users = searchByUser(query);
			
			String[] usersInfo = new String[users.size()];
			
			for (int i = 0; i < users.size(); i++) {
				usersInfo[i] = users.get(i).getLogin();
			}
			
			request.setAttribute("users", usersInfo);
			request.setAttribute("searchType", "user");
			
			request.getRequestDispatcher("searchResults.jsp").forward(request, response);
		}
		else {
			System.out.println("SearchType parameter doesn't have proper value.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private ArrayList<Recipe> searchByRecipe(String query) {
		RecipeDao recipeDao = new RecipeDaoImpl();
		ArrayList<Recipe> recipes = recipeDao.loadAllRecipes();
		
		ArrayList<Recipe> wantedRecipes = new ArrayList<Recipe>();
		
		for (int i = 0; i < recipes.size(); i++) {
			Recipe recipe = recipes.get(i);
			
			if (recipe.getName().contains(query)) {
				wantedRecipes.add(recipe);
			}
		}
		
		return wantedRecipes;
	}
	
	private ArrayList<User> searchByUser(String query) {
		UserDao userDao = new UserDaoImpl();
		ArrayList<User> users = userDao.loadAllUsers();
		
		ArrayList<User> wantedUsers = new ArrayList<User>();
		
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			
			if (user.getLogin().contains(query)) {
				wantedUsers.add(user);
			}
		}
		
		return wantedUsers;
	}

}
