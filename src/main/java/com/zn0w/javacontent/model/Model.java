package com.zn0w.javacontent.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.zn0w.javacontent.db.DBConnector;

public class Model {
	
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	public Model() {
		loadRecipesInfo();
	}
	
	public void loadRecipesInfo() {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from recipes");
			
			while (rs.next()) {
				Recipe recipe = new Recipe();
				
				recipe.setName(rs.getString(2));
				recipe.setIngridients(rs.getString(3));
				recipe.setDescription(rs.getString(4));
				recipe.setAuthorLogin(rs.getString(5));
				recipe.setAuthorName(rs.getString(6));
				
				recipes.add(recipe);
			}
			
			System.out.println("Recipes from database have been loaded.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(ArrayList<Recipe> recipes) {
		this.recipes = recipes;
	}
	
}
