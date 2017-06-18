package com.zn0w.recipesharing.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.zn0w.recipesharing.domain.Comment;
import com.zn0w.recipesharing.domain.Recipe;

public class CommentDaoImpl implements CommentDao {

	public ArrayList<Comment> getRecipeComments(int id) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		
		DBHandler dbHandler = new DBHandler();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from comments where recipe_id = '" + id + "'");
			
			while (rs.next()) {
				Comment comment = new Comment(rs.getString(2), rs.getString(3));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbHandler.close(rs);
			dbHandler.close(statement);
			dbHandler.close(connection);
		}
		
		return comments;
	}

	public void saveComment(int recipeId, String author, String content) {
		DBHandler dbHandler = new DBHandler();
		
		Connection connection = dbHandler.getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate("insert into comments(comment_author, comment_content, recipe_id) values('"+ author +"', '"+ content +"', '"+ recipeId +"')");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbHandler.close(statement);
			dbHandler.close(connection);
		}
	}
	
}
