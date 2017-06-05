package com.zn0w.javacontent.model;

import java.util.ArrayList;

public class Recipe {
	
	private ArrayList<String> ingredientsList = new ArrayList<String>();
	private String description, name, authorLogin, ingredients;
	private int id;
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public String getIngredient(int index) {
		return ingredientsList.get(index);
	}
	
	public void setIngredients(String ingridients) {
		this.ingredients = ingridients;
		
		String ingridient = "";
		
		for (int i = 0; i < ingridients.length(); i++) {
			if (ingridients.charAt(i) == ',') {
				ingredientsList.add(ingridient);
				ingridient = "";
			}
			else if (ingridients.charAt(i) == '.') {
				ingredientsList.add(ingridient);
				ingridient = "";
			}
			else 
				ingridient += ingridients.charAt(i);
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
		return ingredients;
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

	public String getAuthorLogin() {
		return authorLogin;
	}

	public void setAuthorLogin(String authorLogin) {
		this.authorLogin = authorLogin;
	}
	
}
