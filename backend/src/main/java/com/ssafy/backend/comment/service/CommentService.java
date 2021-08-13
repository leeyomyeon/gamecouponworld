package com.ssafy.backend.comment.service;

import java.util.List;

import com.ssafy.backend.comment.model.CommentModel;

public interface CommentService {
	public int boardComment(CommentModel model) throws Exception;
	
	public int feedComment(CommentModel model) throws Exception;
	
	public List<CommentModel> getBoardComment(int boardID) throws Exception;
	
	public List<CommentModel> getFeedComment(int feedID) throws Exception;
	
	int recommendUp(int commentID) throws Exception;
	
	int recommendDown(int commentID) throws Exception;
	
	int deleteComment(int commentID) throws Exception;
}
