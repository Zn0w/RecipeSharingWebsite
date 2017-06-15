package com.zn0w.recipesharing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zn0w.recipesharing.domain.Comment;

public class CommentTest {
	
	@Test
	public void checkInitialization() {
		Comment comment = new Comment("Joe", "Awesome!");
		
		assertEquals(comment.getAuthor(), "Joe");
		assertEquals(comment.getContent(), "Awesome!");
	}
	
}
