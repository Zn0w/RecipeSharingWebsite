package com.zn0w.javacontent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.zn0w.javacontent.domain.Recipe;
import com.zn0w.javacontent.domain.User;

public class UserModel {
	
	private ArrayList<User> users = new ArrayList<User>();
	
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
		} finally {
			if (dbConnector != null) {
				try {
					dbConnector.getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public User loadUser(String login, String password) {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from users");
			
			while (rs.next()) {
				if (rs.getString(2).equals(login) && rs.getString(3).equals(password)) {
					User user = new User(rs.getString(2), rs.getString(3), rs.getString(4));
					
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (dbConnector != null) {
				try {
					dbConnector.getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	public User loadUser(String login) {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from users");
			
			while (rs.next()) {
				if (rs.getString(2).equals(login)) {
					User user = new User(rs.getString(2), rs.getString(3), rs.getString(4));
					
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (dbConnector != null) {
				try {
					dbConnector.getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	public ArrayList<Recipe> loadUserRecipes(String login) {
		DBConnector dbConnector = new DBConnector();
		
		ArrayList<Recipe> userRecipes = new ArrayList<Recipe>();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from recipes where recipe_author_login = '"+login+"'");
			
			while (rs.next()) {
				Recipe recipe = new Recipe();
				
				recipe.setID(rs.getInt(1));
				recipe.setName(rs.getString(2));
				recipe.setIngredients(rs.getString(3));
				recipe.setDescription(rs.getString(4));
				recipe.setAuthorLogin(rs.getString(5));
				
				userRecipes.add(recipe);
			}
			
			System.out.println("User recipes have been loaded.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (dbConnector != null) {
				try {
					dbConnector.getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return userRecipes;
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
		} finally {
			if (dbConnector != null) {
				try {
					dbConnector.getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
	
	public void registerUser(User user) {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			statement.executeUpdate("insert into users(user_login, user_password) values('"+user.getLogin()+"', '"+user.getPassword()+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (dbConnector != null) {
				try {
					dbConnector.getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
}
