package com.zn0w.javacontent.model.user;

import java.util.ArrayList;

import com.zn0w.javacontent.model.Recipe;

public class User {
	
	private String name, login, password;
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	public User() {
		
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

	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(ArrayList<Recipe> recipes) {
		this.recipes = recipes;
	}
	
}
