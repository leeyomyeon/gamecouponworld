package com.ssafy.backend.feed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ssafy.backend.feed.mapper.FeedMapper;
import com.ssafy.backend.feed.model.FeedModel;

@Service
public class FeedServiceImpl implements FeedService {
	@Autowired
	FeedMapper mapper;
	
	@Override
	public int writeFeed(FeedModel model) throws Exception {
		return mapper.writeFeed(model);
	}

	@Override
	public int deleteFeed(int feedID, String userID) throws Exception {
		return mapper.deleteFeed(feedID, userID);
	}

	@Override
	public int incLikeCnt(int feedID) throws Exception {
		return mapper.incLikeCnt(feedID);
	}

	@Override
	public int decLikeCnt(int feedID) throws Exception {
		return mapper.decLikeCnt(feedID);
	}
	
	@Override
	public List<FeedModel> getFeedList(String userID) throws Exception {
		return mapper.getFeedList(userID);
	}

	@Override
	public int insertLikeFeed(@Param("feedID") int feedID, @Param("userID") String userID) throws Exception {
		return mapper.insertLikeFeed(feedID, userID);
	}
		
	@Override
	public int deleteLikeFeed(@Param("feedID") int feedID, @Param("userID") String userID) throws Exception {
		return mapper.deleteLikeFeed(feedID, userID);
	}
	
	@Override
	public List<FeedModel> getMyTimeline(String userID) throws Exception {
		return mapper.getMyTimeline(userID);
	}
	
	@Override
	public List<FeedModel> likeFeedList(String userID) throws Exception {
		return mapper.likeFeedList(userID);
	}

	@Override
	public List<FeedModel> mainFeedList() throws Exception {
		return mapper.mainFeedList();
	}

	@Override
	public FeedModel getFeed(int feedID) throws Exception {
		return mapper.getFeed(feedID);
	}


}
