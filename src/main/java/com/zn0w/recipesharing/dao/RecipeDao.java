package com.zn0w.recipesharing.dao;

import java.util.ArrayList;

import com.zn0w.recipesharing.domain.Recipe;
import com.zn0w.recipesharing.domain.User;

public interface RecipeDao {
	
	public ArrayList<Recipe> loadAllRecipes();
	
	public Recipe loadRecipe(String recipeName, String author);
	
	public Recipe loadRecipe(int id);
	
	public void saveNewRecipe(Recipe recipe);
	
	public void deleteRecipe(Recipe recipe);
	
	public void addRecipeToFavorites(Recipe recipe, User user);
	
	public void removeRecipeFromFavorites(Recipe recipe, User user);
	
}
