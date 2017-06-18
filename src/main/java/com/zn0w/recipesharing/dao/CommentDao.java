package com.zn0w.recipesharing.dao;

import java.util.ArrayList;

import com.zn0w.recipesharing.domain.Comment;
import com.zn0w.recipesharing.domain.Recipe;

public interface CommentDao {
	
	public ArrayList<Comment> getRecipeComments(int id);
	public void saveComment(int recipeId, String author, String content);
	
}
