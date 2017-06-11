package com.zn0w.javacontent.domain;

import java.util.ArrayList;

import com.zn0w.javacontent.dao.RecipeModel;
import com.zn0w.javacontent.dao.UserModel;

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
		
		RecipeModel recipeModel = new RecipeModel();
		UserModel userModel = new UserModel();
		
		if (userFavouritesStr != null) {
			char[] userFavouritesDivided = userFavouritesStr.toCharArray();
			
			String id = "";
			
			for (int i = 0; i < userFavouritesDivided.length; i++) {
				if (userFavouritesDivided[i] == ' ') {
					if (!id.equals("")) {
						System.out.println("ID: " + id);
						
						int idInt = Integer.parseInt(id);
						
						Recipe recipe = recipeModel.loadRecipe(idInt);
						
						if (recipe != null)
							favouritedRecipes.add(recipe);
						
						id = "";
					}
				}
				else
					id += userFavouritesDivided[i];
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
