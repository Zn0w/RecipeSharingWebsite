package com.zn0w.javacontent.model;

import java.util.ArrayList;

public class Recipe {
	
	private ArrayList<String> ingredientsList = new ArrayList<String>();
	private String id, description, name, authorName, authorLogin, ingridients;
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getID() {
		return id;
	}
	
	public String getIngredient(int index) {
		return ingredientsList.get(index);
	}
	
	public void setIngredients(String ingridients) {
		this.ingridients = ingridients;
		
		String ingridient = "";
		
		for (int i = 0; i < ingridients.length(); i++) {
			if (ingridients.charAt(i) != ',')
				ingridient += ingridients.charAt(i);
			else {
				ingredientsList.add(ingridient);
				ingridient = "";
			}
		}
	}
	
	public String[] getIngredientsArray() {
		String[] ingredients = new String[ingredientsList.size()];
		
		for (int i = 0; i < ingredientsList.size(); i++) {
			ingredients[i] = ingredientsList.get(i);
		}
		
		return ingredients;
	}
	
	public String getIngredients() {
		return ingridients;
	}
	
	public ArrayList<String> getIngredientsList() {
		return ingredientsList;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorLogin() {
		return authorLogin;
	}

	public void setAuthorLogin(String authorLogin) {
		this.authorLogin = authorLogin;
	}
	
}
