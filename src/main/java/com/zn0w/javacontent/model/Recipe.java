package com.zn0w.javacontent.model;

import java.util.ArrayList;

public class Recipe {
	
	private ArrayList<String> ingridientsList = new ArrayList<String>();
	private String description, name, authorName, authorLogin, ingridients;
	
	public String getIngridient(int index) {
		return ingridientsList.get(index);
	}
	
	public void setIngridients(String ingridients) {
		/*String ingridient = "";
		
		for (int i = 0; i < ingridients.length(); i++) {
			if (ingridients.charAt(i) != ',')
				ingridient += ingridients.charAt(i);
			else {
				ingridientsList.add(ingridient);
				ingridient = "";
			}
		}*/
		this.ingridients = ingridients;
	}
	
	public String getIngridients() {
		return ingridients;
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
