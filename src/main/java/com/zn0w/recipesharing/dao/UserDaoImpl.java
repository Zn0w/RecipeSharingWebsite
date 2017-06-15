package com.zn0w.recipesharing.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.zn0w.recipesharing.domain.Recipe;
import com.zn0w.recipesharing.domain.User;

public class UserDaoImpl implements UserDao {
	
	public ArrayList<User> loadAllUsers() {
		DBHandler dbHandler = new DBHandler();
		
		ArrayList<User> users = new ArrayList<User>();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from users");
			
			while (rs.next()) {
				User user = new User(rs.getString(2), rs.getString(3), rs.getString(4));
				
				users.add(user);
			}
			
			System.out.println("Users from database have been loaded.");
			
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbHandler.close(rs);
			dbHandler.close(statement);
			dbHandler.close(connection);
		}
		return users;
	}
	
	public User loadUser(String login, String password) {
		DBHandler dbHandler = new DBHandler();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from users");
			
			while (rs.next()) {
				if (rs.getString(2).equals(login) && rs.getString(3).equals(password)) {
					User user = new User(rs.getString(2), rs.getString(3), rs.getString(4));
					
					return user;
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
	
	public User loadUser(String login) {
		DBHandler dbHandler = new DBHandler();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from users");
			
			while (rs.next()) {
				if (rs.getString(2).equals(login)) {
					User user = new User(rs.getString(2), rs.getString(3), rs.getString(4));
					
					return user;
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
	
	public ArrayList<Recipe> loadUserRecipes(String login) {
		DBHandler dbHandler = new DBHandler();
		
		ArrayList<Recipe> userRecipes = new ArrayList<Recipe>();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from recipes where recipe_author_login = '"+login+"'");
			
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
			dbHandler.close(rs);
			dbHandler.close(statement);
			dbHandler.close(connection);
		}
		
		return userRecipes;
	}
	
	public boolean loginIsCreated(String login) {
		DBHandler dbHandler = new DBHandler();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from users");
			
			while (rs.next()) {
				if (rs.getString(2).equals(login))
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
	
	public void registerUser(User user) {
		DBHandler dbHandler = new DBHandler();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate("insert into users(user_login, user_password) values('"+user.getLogin()+"', '"+user.getPassword()+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbHandler.close(statement);
			dbHandler.close(connection);
		}
	}
	
}
