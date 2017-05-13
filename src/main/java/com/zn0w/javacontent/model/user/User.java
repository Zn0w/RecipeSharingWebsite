package com.zn0w.javacontent.model.user;

import java.util.ArrayList;

import com.zn0w.javacontent.model.Recipe;

public class User {
	
	private String name, login, password;
	private ArrayList<Recipe> userRecipes = new ArrayList<Recipe>();
	private ArrayList<Recipe> favouritedRecipe = new ArrayList<Recipe>();
	
	public User(String login, String name, String password) {
		this.login = login;
		this.name = name;
		this.password = password;
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

	public ArrayList<Recipe> getFavouritedRecipe() {
		return favouritedRecipe;
	}

	public void setFavouritedRecipe(ArrayList<Recipe> favouritedRecipe) {
		this.favouritedRecipe = favouritedRecipe;
	}
	
}
