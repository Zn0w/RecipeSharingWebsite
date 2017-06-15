package com.zn0w.recipesharing.dao;

import java.util.ArrayList;

import com.zn0w.recipesharing.domain.Recipe;
import com.zn0w.recipesharing.domain.User;

public interface UserDao {
	
	public ArrayList<User> loadAllUsers();
	
	public User loadUser(String login);
	
	public ArrayList<Recipe> loadUserRecipes(String login);
	
	public void registerUser(User user);
	
	public boolean loginIsCreated(String login);
	
}
