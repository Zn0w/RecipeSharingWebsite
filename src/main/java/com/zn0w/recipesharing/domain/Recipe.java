package com.zn0w.recipesharing.domain;

import java.util.ArrayList;

import com.zn0w.recipesharing.dao.CommentDao;
import com.zn0w.recipesharing.dao.CommentDaoImpl;

public class Recipe {
	
	private ArrayList<String> ingredientsList = new ArrayList<String>();
	private String description, name, authorLogin, ingredients;
	private int id;
	
	private ArrayList<Comment> comments = new ArrayList<Comment>();
	
	public String getIngredient(int index) {
		return ingredientsList.get(index);
	}
	
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
		
		String[] ingredientsDivided = ingredients.split(",");
		
		for (int i = 0; i < ingredientsDivided.length; i++) {
			ingredientsList.add(ingredientsDivided[i]);
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
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}

	public String[][] getComments() {
		String[][] commentsInfo = null;
		
		if (comments.size() > 0) {
			commentsInfo = new String[comments.size()][2];
			
			for (int i = 0; i < comments.size(); i++) {
				Comment comment = comments.get(i);
				
				commentsInfo[i][0] = comment.getAuthor();
				commentsInfo[i][1] = comment.getContent();
			}
		}
		
		return commentsInfo;
	}
	
	public void setComments() {
		CommentDao commentDao = new CommentDaoImpl();
		comments = commentDao.getRecipeComments(id);
	}
	
}
