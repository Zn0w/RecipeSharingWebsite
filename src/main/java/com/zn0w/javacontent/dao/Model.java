package com.zn0w.javacontent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.zn0w.javacontent.domain.Recipe;
import com.zn0w.javacontent.domain.User;

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
				
				recipe.setID(rs.getInt(1));
				recipe.setName(rs.getString(2));
				recipe.setIngredients(rs.getString(3));
				recipe.setDescription(rs.getString(4));
				recipe.setAuthorLogin(rs.getString(5));
				
				recipes.add(recipe);
			}
			
			System.out.println("Recipes from database have been loaded.");
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
	
	public Recipe loadRecipe(String recipeName, String author) {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from recipes");
			
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
	
	public Recipe loadRecipe(int id) {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from recipes");
			
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
	
	public boolean recipeWithNameExists(String recipeName, String author) {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from recipes");
			
			while (rs.next()) {
				if (rs.getString(2).equals(recipeName) && rs.getString(5).equals(author))
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
	
	public void saveNewRecipe(Recipe recipe) {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			statement.executeUpdate("insert into recipes(recipe_name, recipe_ingridients, recipe_description, recipe_author_login) values('"+recipe.getName()+"', '"+recipe.getIngredients()+"', '"+recipe.getDescription()+"', '"+recipe.getAuthorLogin()+"')");
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
	
	public void deleteRecipe(Recipe recipe) {
		DBConnector dbConnector = new DBConnector();
		
		Statement statement;
		try {
			statement = dbConnector.getConnection().createStatement();
			statement.executeUpdate("delete from recipes where id = '"+ recipe.getID() +"'");
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
	
	public void addRecipeToFavorites(Recipe recipe, User user) {
		DBConnector dbConnector = new DBConnector();
		
		Statement statement;
		
		ArrayList<Recipe> favorites = user.getFavouritedRecipes();
		favorites.add(recipe);
		
		String favoritesStr = "";
		for (int i = 0; i < favorites.size(); i++) {
			favoritesStr += favorites.get(i).getID() + " ";
		}
		
		try {
			statement = dbConnector.getConnection().createStatement();
			statement.executeUpdate("update users set user_favourites = '" + favoritesStr + "' where user_login = '" + user.getLogin() + "'");
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
	
	public void removeRecipeFromFavorites(Recipe recipe, User user) {
		DBConnector dbConnector = new DBConnector();
		
		try {
			Statement statement = dbConnector.getConnection().createStatement();
			
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
			if (dbConnector != null) {
				try {
					dbConnector.getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
