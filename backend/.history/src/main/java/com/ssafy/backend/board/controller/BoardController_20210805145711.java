package com.ssafy.backend.board.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.backend.board.model.BoardModel;
import com.ssafy.backend.board.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/board")
@Api("Board Controller API")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// 글쓰기
	@ApiOperation(value = "글쓰기", notes = "게시판에 글을 쓸 수 있습니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "글쓰기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/write")
	public ResponseEntity<?> writeBoard(@RequestBody BoardModel model) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Date time = new Date();
  
        model.setdate(format.format(time));
        model.setRecommend(0);
        model.setViewCount(1);
        int res = service.writeBoard(model);
        
        if(res >= 1) {
        	return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
	}
	
	// 글 목록 불러오기
	// limit = 출력할 행의 수
	// offset = 몇번째 row부터 출력할 것인지(0부터 시작)
	@ApiOperation(value = "글 목록 불러오기", notes = "게시판의 모든 글을 불러옵니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "글 목록 불러오기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/list/{limit}/{offset}")
	public ResponseEntity<?> boardList(@PathVariable("limit") int limit, @PathVariable("offset") int offset) throws Exception {
		List<BoardModel> list = service.selectBoardLimitOffset(limit, offset);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 글 삭제
	// 게시글 번호(테이블의 PK)로 접근함, 값 보낼 때 boardID를 보내야 함
	@ApiOperation(value = "게시글 삭제", notes = "게시판의 글을 삭제합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "글 삭제 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@DeleteMapping("/delete/{boardID}")
	public ResponseEntity<?> deleteBoard(@PathVariable("boardID") int boardID) throws Exception {
		int res = service.deleteBoard(boardID);
		
		if(res >= 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 글 수정
	@ApiOperation(value = "글 수정하기", notes = "해당 글을 수정하고 업데이트합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "글 수정하기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PutMapping("/update")
	public ResponseEntity<?> updateBoard(@RequestBody BoardModel model) throws Exception {
		int res = service.updateBoard(model);
		
		if(res >= 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 게시글 검색
	// searchOption = 검색옵션(내용, 제목, 작성자)
	// searchOption 값 보낼때 내용 = content, 작성자 = writer, 제목 = title로 보내기
	// value = 포함될 값
	// limit = 출력할 행의 수
	// offset = 몇번째 row부터 출력할 것인지(0부터 시작)
	@ApiOperation(value = "글 검색하기", notes = "게시판의 글을 검색하여 리스트를 불러옵니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "글 목록 불러오기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/search/{searchOption}/{value}/{limit}/{offset}")
	public ResponseEntity<?> searchBoard(@PathVariable("searchOption") String searchOption,
										 @PathVariable("value") String value,
										 @PathVariable("limit") int limit,
										 @PathVariable("offset") int offset) throws Exception {
		
		List<BoardModel> list = service.searchBoard(searchOption, value, limit, offset);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 게시글 내용 보기(클릭 시 조회수 올라가게)
	@ApiOperation(value = "글 내용 보기", notes = "게시판의 글 내용을 봅니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "게시글 내용 불러오기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/select/{boardID}")
	public ResponseEntity<?> selectBoard(@PathVariable("boardID") int boardID) throws Exception {
		BoardModel model = service.selectBoard(boardID);
		
        if (model == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        
        int cnt = model.getViewCount();
        model.setViewCount(cnt + 1);
        service.updateViewCount(boardID, cnt);
        
        return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	// 전체 게시글 수 가져오기
	@ApiOperation(value = "전체 게시글 수 불러오기", notes = "게시판의 모든 글의 개수를 반환합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "글 개수 반환하기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/totalCnt")
	public ResponseEntity<?> totalCount() throws Exception {
		int cnt = service.totalCount();
		
		return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
	}
}
