package com.zn0w.recipesharing.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie loginCookie = null;
		
    	Cookie[] cookies = request.getCookies();
    	if (cookies != null) {
    		for (Cookie cookie : cookies) {
    			if (cookie.getName().equals("username")) {
    				loginCookie = cookie;
    				break;
    			}
    		}
    	}
    	
    	if (loginCookie != null) {
    		loginCookie.setMaxAge(0);
    		response.addCookie(loginCookie);
    	}
    	
    	response.sendRedirect("index.jsp");
	}
}
