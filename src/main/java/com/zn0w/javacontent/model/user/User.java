package com.zn0w.javacontent.model.user;

import java.util.ArrayList;

import com.zn0w.javacontent.model.Model;
import com.zn0w.javacontent.model.Recipe;

public class User {
	
	private String name, login, password;
	private ArrayList<Recipe> userRecipes = new ArrayList<Recipe>();
	private ArrayList<Recipe> favouritedRecipes = new ArrayList<Recipe>();
	
	public User(String login, String name, String password) {
		this.login = login;
		this.name = name;
		this.password = password;
	}
	
	public User(String login, String name, String password, String userRecipesStr, String userFavouritesStr) {
		this.login = login;
		this.name = name;
		this.password = password;
		
		char[] userRecipesDivided = null;
		char[] userFavouritesDivided = null;
		
		if (userRecipesStr != null)
			userRecipesDivided = userRecipesStr.toCharArray();
		
		if (userFavouritesStr != null)
			userFavouritesDivided = userFavouritesStr.toCharArray();
		
		Model model = new Model();
		model.loadRecipesInfo();
		
		ArrayList<Recipe> recipes = model.getRecipes();
		
		if (userRecipesDivided != null) {
			for (int i = 0; i < userRecipesDivided.length; i++) {
				if (userRecipesDivided[i] == ' ') {
					continue;
				}
			
			for (int j = 0; j < recipes.size(); j++) {
				if (recipes.get(j).getID().equals(userRecipesDivided[i]))
					userRecipes.add(recipes.get(j));
				}
			}
		}
		
		if (userFavouritesDivided != null) {
			for (int i = 0; i < userFavouritesDivided.length; i++) {
				if (userFavouritesDivided[i] == ' ') {
					continue;
				}
				
				for (int j = 0; j < recipes.size(); j++) {
					if (recipes.get(j).getID().equals(userFavouritesDivided[i]))
						favouritedRecipes.add(recipes.get(j));
				}
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
