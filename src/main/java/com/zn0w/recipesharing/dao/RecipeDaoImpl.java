package com.zn0w.recipesharing.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.zn0w.recipesharing.domain.Recipe;
import com.zn0w.recipesharing.domain.User;

public class RecipeDaoImpl implements RecipeDao {
	
	public ArrayList<Recipe> loadAllRecipes() {
		DBHandler dbHandler = new DBHandler();
		
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from recipes");
			
			while (rs.next()) {
				Recipe recipe = new Recipe();
				
				recipe.setID(rs.getInt(1));
				recipe.setName(rs.getString(2));
				recipe.setIngredients(rs.getString(3));
				recipe.setDescription(rs.getString(4));
				recipe.setAuthorLogin(rs.getString(5));
				
				recipes.add(recipe);
			}
			
			System.out.println("Recipes from database have been loaded.");
			
			return recipes;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbHandler.close(rs);
			dbHandler.close(statement);
			dbHandler.close(connection);
		}
		return recipes;
	}
	
	public Recipe loadRecipe(String recipeName, String author) {
		DBHandler dbHandler = new DBHandler();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from recipes");
			
			while (rs.next()) {
				if (rs.getString(2).equals(recipeName)) {
					Recipe recipe = new Recipe();
					
					recipe.setID(rs.getInt(1));
					recipe.setName(rs.getString(2));
					recipe.setIngredients(rs.getString(3));
					recipe.setDescription(rs.getString(4));
					recipe.setAuthorLogin(rs.getString(5));
					
					return recipe;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbHandler.close(rs);
			dbHandler.close(statement);
			dbHandler.close(connection);
		}
		
		return null;
	}
	
	public Recipe loadRecipe(int id) {
		DBHandler dbHandler = new DBHandler();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from recipes");
			
			while (rs.next()) {
				if (rs.getInt(1) == id) {
					Recipe recipe = new Recipe();
					
					recipe.setID(rs.getInt(1));
					recipe.setName(rs.getString(2));
					recipe.setIngredients(rs.getString(3));
					recipe.setDescription(rs.getString(4));
					recipe.setAuthorLogin(rs.getString(5));
					
					return recipe;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbHandler.close(rs);
			dbHandler.close(statement);
			dbHandler.close(connection);
		}
		
		return null;
	}
	
	public boolean recipeWithNameExists(String recipeName, String author) {
		DBHandler dbHandler = new DBHandler();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from recipes");
			
			while (rs.next()) {
				if (rs.getString(2).equals(recipeName) && rs.getString(5).equals(author))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbHandler.close(rs);
			dbHandler.close(statement);
			dbHandler.close(connection);
		}
		
		return false;
	}
	
	public void saveNewRecipe(Recipe recipe) {
		DBHandler dbHandler = new DBHandler();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate("insert into recipes(recipe_name, recipe_ingridients, recipe_description, recipe_author_login) values('"+recipe.getName()+"', '"+recipe.getIngredients()+"', '"+recipe.getDescription()+"', '"+recipe.getAuthorLogin()+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbHandler.close(statement);
			dbHandler.close(connection);
		}
	}
	
	public void deleteRecipe(Recipe recipe) {
		DBHandler dbHandler = new DBHandler();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate("delete from recipes where id = '"+ recipe.getID() +"'");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbHandler.close(statement);
			dbHandler.close(connection);
		}
	}
	
	public void addRecipeToFavorites(Recipe recipe, User user) {
		DBHandler dbHandler = new DBHandler();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		
		ArrayList<Recipe> favorites = user.getFavouritedRecipes();
		favorites.add(recipe);
		
		String favoritesStr = "";
		for (int i = 0; i < favorites.size(); i++) {
			favoritesStr += favorites.get(i).getID() + " ";
		}
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate("update users set user_favourites = '" + favoritesStr + "' where user_login = '" + user.getLogin() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbHandler.close(statement);
			dbHandler.close(connection);
		}
	}
	
	public void removeRecipeFromFavorites(Recipe recipe, User user) {
		DBHandler dbHandler = new DBHandler();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			
			ArrayList<Recipe> userFavorites = user.getFavouritedRecipes();
			int recipeId = recipe.getID();
			
			String userFavoritesUpdated = "";
			
			for (int i = 0; i < userFavorites.size(); i++) {
				if (userFavorites.get(i).getID() != recipeId) {
					userFavoritesUpdated += userFavorites.get(i).getID() + " ";
				}
			}
			
			System.out.println(userFavoritesUpdated);
			
			if (userFavoritesUpdated != "") {
				statement.executeUpdate("update users set user_favourites = '"+ userFavoritesUpdated +"' where user_login = '"+ user.getLogin() +"'");
			}
			else
				System.out.println("User favorites is blank.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbHandler.close(statement);
			dbHandler.close(connection);
		}
	}
	
}
