package com.zn0w.javacontent.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.zn0w.javacontent.db.DBConnector;
import com.zn0w.javacontent.model.user.User;

public class Model {
	
	private DBConnector dbConnector = new DBConnector();
	
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	private ArrayList<User> users = new ArrayList<User>();
	
	public void loadRecipesInfo() {
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
	
	public boolean loginIsCreated(String login) {
		ResultSet rs = null;
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			rs = statement.executeQuery("select * form users where user_login = '"+login+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (rs == null)
			return false;
		else
			return true;
	}
	
	public void registerUser(User user) {
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
