package com.zn0w.recipesharing.domain;

import java.util.ArrayList;

import com.zn0w.recipesharing.dao.RecipeDao;
import com.zn0w.recipesharing.dao.RecipeDaoImpl;
import com.zn0w.recipesharing.dao.UserDao;
import com.zn0w.recipesharing.dao.UserDaoImpl;

public class User {
	
	private String login, password;
	private ArrayList<Recipe> userRecipes = new ArrayList<Recipe>();
	private ArrayList<Recipe> favouritedRecipes = new ArrayList<Recipe>();
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public User(String login, String password, String userFavouritesStr) {
		System.out.println("Favorites: " + userFavouritesStr);
		
		this.login = login;
		this.password = password;
		
		RecipeDao recipeModel = new RecipeDaoImpl();
		UserDao userModel = new UserDaoImpl();
		
		if (userFavouritesStr != null) {
			String[] idsStr = userFavouritesStr.split(" ");
			
			for (int i = 0; i < idsStr.length; i++) {
				int id = Integer.parseInt(idsStr[i]);
				Recipe recipe = recipeModel.loadRecipe(id);
				
				favouritedRecipes.add(recipe);
			}
		}
		
		userRecipes = userModel.loadUserRecipes(login);
		
		for (int i = 0; i < userRecipes.size(); i++) {
			System.out.println(userRecipes.get(i).getName());
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Recipe> getUserRecipes() {
		return userRecipes;
	}

	public void setUserRecipes(ArrayList<Recipe> userRecipes) {
		this.userRecipes = userRecipes;
	}

	public ArrayList<Recipe> getFavouritedRecipes() {
		return favouritedRecipes;
	}

	public void setFavouritedRecipes(ArrayList<Recipe> favouritedRecipes) {
		this.favouritedRecipes = favouritedRecipes;
	}
	
}
