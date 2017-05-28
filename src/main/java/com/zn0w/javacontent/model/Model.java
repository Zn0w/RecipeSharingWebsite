package com.zn0w.javacontent.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.zn0w.javacontent.db.DBConnector;
import com.zn0w.javacontent.model.user.User;

public class Model {
	
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	private ArrayList<User> users = new ArrayList<User>();
	
	public void loadRecipesInfo() {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from recipes");
			
			while (rs.next()) {
				Recipe recipe = new Recipe();
				
				recipe.setName(rs.getString(2));
				recipe.setIngredients(rs.getString(3));
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
	
	public Recipe loadRecipe(String recipeName, String author) {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from recipes");
			
			while (rs.next()) {
				if (rs.getString(2).equals(recipeName) && rs.getString(6).equals(author)) {
					Recipe recipe = new Recipe();
					
					recipe.setName(rs.getString(2));
					recipe.setIngredients(rs.getString(3));
					recipe.setDescription(rs.getString(4));
					recipe.setAuthorLogin(rs.getString(5));
					recipe.setAuthorName(rs.getString(6));
					
					return recipe;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void loadMinimumUsersInfo() {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from users");
			
			while (rs.next()) {
				User user = new User(rs.getString(2), rs.getString(3), rs.getString(4));
				
				users.add(user);
			}
			
			System.out.println("Users from database have been loaded.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User loadUser(String login, String password) {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from users");
			
			while (rs.next()) {
				if (rs.getString(2).equals(login) && rs.getString(4).equals(password)) {
					User user = new User(rs.getString(2), rs.getString(3), rs.getString(4));
					
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean loginIsCreated(String login) {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from users");
			
			while (rs.next()) {
				if (rs.getString(2).equals(login))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean nameIsCreated(String name) {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from users");
			
			while (rs.next()) {
				if (rs.getString(3).equals(name))
					return true;
				else
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void registerUser(User user) {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			statement.executeUpdate("insert into users(user_login, user_name, user_password) values('"+user.getLogin()+"', '"+user.getName()+"', '"+user.getPassword()+"')");
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

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
}
