package com.ssafy.backend.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.backend.comment.mapper.CommentMapper;
import com.ssafy.backend.comment.model.CommentModel;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	public CommentMapper mapper;
	
	// 게시판에 코멘트 달기
	public int boardComment(CommentModel model) throws Exception {
		return mapper.boardComment(model);
	}
	
	// 상대방 피드에 코멘트 달기
	public int feedComment(CommentModel model) throws Exception {
		return mapper.feedComment(model);
	}
	
	// 게시글 클릭하면 코멘트 불러오기
	public List<CommentModel> getBoardComment(int boardID) throws Exception {
		return mapper.getBoardComment(boardID);
	}
	
	// 피드 클릭하면 코멘트 불러오기
	public List<CommentModel> getFeedComment(int feedID) throws Exception {
		return mapper.getFeedComment(feedID);
	}
	
	// 코멘트 추천
	public int recommendUp(int commentID) throws Exception {
		return mapper.recommendUp(commentID);
	}
	
	// 코멘트 비추천
	public int recommendDown(int commentID) throws Exception {
		return mapper.recommendDown(commentID);
	}
	
	// 코멘트 삭제
	public int deleteComment(int commentID) throws Exception {
		return mapper.deleteComment(commentID);
	}
	
}
