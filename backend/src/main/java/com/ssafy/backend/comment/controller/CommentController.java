package com.ssafy.backend.comment.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.backend.comment.model.CommentModel;
import com.ssafy.backend.comment.service.CommentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService service;
	
	static SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	
	@ApiOperation(value = "게시판에 코멘트 작성하기", notes = "게시판에 코멘트를 작성합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "코멘트 작성 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/registBoard")
	public ResponseEntity<?> registBoardComment(@RequestBody CommentModel model) throws Exception {
		Date time = new Date();
		model.setDate(format.format(time));
		
		int res = service.boardComment(model);
		
		if(res >= 1) {
			return new ResponseEntity<>(model, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "피드에 코멘트 등록", notes = "피드에 코멘트를 등록합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "코멘트 등록 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/registFeed")
	public ResponseEntity<?> registFeedComment(@RequestBody CommentModel model) throws Exception {
		Date time = new Date();
		model.setDate(format.format(time));
		
		int res = service.feedComment(model);
		
		if(res >= 1) {
			return new ResponseEntity<>(model, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "게시판의 코멘트 불러오기", notes = "게시판의 코멘트를 불러옵니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "코멘트 불러오기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/getBoard/{boardID}")
	public ResponseEntity<?> getBoardComment(@PathVariable("boardID") int boardID) throws Exception {
		List<CommentModel> list = service.getBoardComment(boardID);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "피드의 코멘트 불러오기", notes = "피드의 모든 코멘트를 불러옵니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "코멘트 불러오기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/getFeed/{feedID}")
	public ResponseEntity<?> getFeedComment(@PathVariable("feedID") int feedID) throws Exception {
		List<CommentModel> list = service.getFeedComment(feedID);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "추천", notes = "해당 코멘트를 추천합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "글 목록 불러오기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/recommendUp")
	public ResponseEntity<?> recommendUp(@RequestBody int commentID) throws Exception {
		service.recommendUp(commentID);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "비추천", notes = "해당 코멘트를 비추천합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "글 목록 불러오기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/recommendDown")
	public ResponseEntity<?> recommendDown(@RequestBody int commentID) throws Exception {
		service.recommendDown(commentID);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "코멘트 삭제", notes = "해당 코멘트를 삭제합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "코멘트 삭제하기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@DeleteMapping("/deleteComment/{commentID}")
	public ResponseEntity<?> deleteComment(@PathVariable int commentID) throws Exception {
		int res = service.deleteComment(commentID);
		
		if(res >= 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);	
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
