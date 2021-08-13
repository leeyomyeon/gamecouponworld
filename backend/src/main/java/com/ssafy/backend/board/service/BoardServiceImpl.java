package com.ssafy.backend.board.service;

import com.ssafy.backend.board.mapper.BoardMapper;
import com.ssafy.backend.board.model.BoardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    public BoardMapper mapper;

    @Override
    public int writeBoard(BoardModel model) throws Exception {
        return mapper.writeBoard(model);
    }

    @Override
    public int deleteBoard(int boardID) throws Exception {
        return mapper.deleteBoard(boardID);
    }

    @Override
    public BoardModel selectBoard(int boardID) throws Exception {
        return mapper.selectBoard(boardID);
    }

    @Override
    public List<BoardModel> searchBoard(String searchOption, String value, int limit, int offset) throws Exception {
        return mapper.searchBoard(searchOption, value, limit, offset);
    }

    @Override
    public int updateBoard(BoardModel model) throws Exception {
        return mapper.updateBoard(model);
    }

    @Override
    public int totalCount() throws Exception {
        return mapper.totalCount();
    }

    @Override
    public List<BoardModel> selectBoardLimitOffset(int limit, int offset) throws Exception {
        return mapper.boardList(limit, offset);
    }
    
    @Override
    public int updateViewCount(int boardID, int viewCount) throws Exception {
    	return mapper.updateViewCount(boardID, viewCount);
    }
}
