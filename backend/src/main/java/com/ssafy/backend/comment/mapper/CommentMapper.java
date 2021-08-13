package com.ssafy.backend.comment.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.comment.model.CommentModel;

@Repository
public interface CommentMapper {
	int boardComment(CommentModel model) throws Exception;
	
	int feedComment(CommentModel model) throws Exception;
	
	List<CommentModel> getBoardComment(int boardID) throws Exception;
	
	List<CommentModel> getFeedComment(int feedID) throws Exception;
	
	int recommendUp(int commentID) throws Exception;
	
	int recommendDown(int commentID) throws Exception;
	
	int deleteComment(int commentID) throws Exception;
}
