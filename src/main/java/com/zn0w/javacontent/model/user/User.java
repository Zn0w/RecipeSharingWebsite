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
	
	public User(String login, String name, String password, String userFavouritesStr) {
		this.login = login;
		this.name = name;
		this.password = password;
		
		char[] userFavouritesDivided = null;
		
		if (userFavouritesStr != null)
			userFavouritesDivided = userFavouritesStr.toCharArray();
		
		Model model = new Model();
		model.loadRecipesInfo();
		
		userRecipes = model.loadUserRecipes(login);
		
		for (int i = 0; i < userRecipes.size(); i++) {
			System.out.println(userRecipes.get(i).getName());
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
