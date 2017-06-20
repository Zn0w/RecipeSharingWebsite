package com.zn0w.recipesharing.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zn0w.recipesharing.dao.CommentDao;
import com.zn0w.recipesharing.dao.CommentDaoImpl;
import com.zn0w.recipesharing.dao.RecipeDao;
import com.zn0w.recipesharing.dao.RecipeDaoImpl;
import com.zn0w.recipesharing.domain.Recipe;

/**
 * Servlet implementation class SendCommentServlet
 */
public class SendCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendCommentServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentAuthor = request.getParameter("commentAuthor");
		String commentContent = request.getParameter("commentContent");
		String recipeName = request.getParameter("recipeName");
		String recipeAuthor = request.getParameter("recipeAuthor");
		
		String recipeUrl = request.getParameter("recipeUrl");
		System.out.println("Recipe URL: " + recipeUrl);
		
		if (commentAuthor.equals("Guest")) {
			response.sendRedirect("login.jsp");
		}
		else {
			RecipeDao recipeDao = new RecipeDaoImpl();
			Recipe recipe = recipeDao.loadRecipe(recipeName, recipeAuthor);
			
			CommentDao commentDao = new CommentDaoImpl();
			commentDao.saveComment(recipe.getID(), commentAuthor, commentContent);
			
			request.setAttribute("recipeName", recipeName);
			request.setAttribute("author", recipeAuthor);
			
			response.sendRedirect(request.getContextPath() + "/RecipeServlet?" + recipeUrl);
		}
	}

}
