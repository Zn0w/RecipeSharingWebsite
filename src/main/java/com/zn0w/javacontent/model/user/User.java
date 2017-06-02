package com.zn0w.javacontent.model.user;

import java.util.ArrayList;

import com.zn0w.javacontent.model.Model;
import com.zn0w.javacontent.model.Recipe;

public class User {
	
	private String login, password;
	private ArrayList<Recipe> userRecipes = new ArrayList<Recipe>();
	private ArrayList<Recipe> favouritedRecipes = new ArrayList<Recipe>();
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public User(String login, String password, String userFavouritesStr) {
		this.login = login;
		this.password = password;
		
		Model model = new Model();
		
		char[] userFavouritesDivided = null;
		
		if (userFavouritesStr != null)
			userFavouritesDivided = userFavouritesStr.toCharArray();
		
		if (userFavouritesDivided != null) {
			for (int i = 0; i < userFavouritesDivided.length; i++) {
				if (userFavouritesDivided[i] == ' ')
					continue;
				else {
					int id = Character.getNumericValue(userFavouritesDivided[i]);
					System.out.println("numeric value is" + id);
					
					Recipe recipe = model.loadRecipe(id);
					
					if (recipe != null)
						favouritedRecipes.add(recipe);
				}
			}
		}
		
		//model.loadRecipesInfo();
		
		userRecipes = model.loadUserRecipes(login);
		
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
