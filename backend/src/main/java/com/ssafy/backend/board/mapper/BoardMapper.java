package com.ssafy.backend.board.mapper;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.backend.board.model.BoardModel;

@Repository
public interface BoardMapper {
    int writeBoard(BoardModel model) throws SQLException;
    
    int deleteBoard(int boardNum) throws SQLException;
    
    int updateBoard(BoardModel model) throws SQLException;
    
    int totalCount() throws SQLException;
    
    BoardModel selectBoard(int boardNum) throws SQLException;
    
    List<BoardModel> boardList(@Param("limit") int limit, @Param("offset") int offset) throws SQLException;
    
    List<BoardModel> searchBoard(@Param("searchOption") String searchOption,
            @Param("value") String value,
            @Param("limit") int limit,
            @Param("offset") int offset) throws SQLException;
    
    int updateViewCount(@Param("boardID") int boardID, @Param("viewCount") int viewCount) throws Exception;
}
