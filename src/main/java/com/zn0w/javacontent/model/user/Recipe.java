package com.zn0w.javacontent.model.user;

import java.util.ArrayList;

public class Recipe {
	
	private ArrayList<String> ingridients = new ArrayList<String>();
	private String description;
	
	public String getIngridient(int index) {
		return ingridients.get(index);
	}
	
	public void setIngridient(String ingridient) {
		ingridients.add(ingridient);
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
