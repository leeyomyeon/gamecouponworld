package com.ssafy.backend.board.service;

import java.util.List;
import org.springframework.data.repository.query.Param;
import com.ssafy.backend.board.model.BoardModel;

public interface BoardService {
    int writeBoard(BoardModel BoardModel) throws Exception;
    
    int deleteBoard(int boardID) throws Exception;
    
    BoardModel selectBoard(int boardID) throws Exception;
    
    List<BoardModel> searchBoard(String searchOption, String value, int limit, int offset) throws Exception;
    
    int updateBoard(BoardModel BoardModel) throws Exception;
    
    public int totalCount() throws Exception;
    
    List<BoardModel> selectBoardLimitOffset(@Param("limit") int limit, @Param("offset") int offset) throws Exception;
    
    int updateViewCount(@Param("boardID") int boardID, @Param("viewCount") int viewCount) throws Exception;
}
