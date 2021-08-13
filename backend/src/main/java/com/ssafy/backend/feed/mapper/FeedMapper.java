package com.ssafy.backend.feed.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.backend.feed.model.FeedModel;

@Repository
public interface FeedMapper {
	int writeFeed(FeedModel model) throws Exception;
	
	int deleteFeed(@Param("feedID") int feedID, @Param("userID") String userID) throws Exception;
	
	int incLikeCnt(int feedID) throws Exception;
	
	int decLikeCnt(int feedID) throws Exception;
	
	List<FeedModel> getFeedList(String userID) throws Exception;
	
	int insertLikeFeed(@Param("feedID") int feedID, @Param("userID") String userID) throws Exception;
	
	int deleteLikeFeed(@Param("feedID") int feedID, @Param("userID") String userID) throws Exception;
	
	List<FeedModel> getMyTimeline(String userID) throws Exception;
	
	List<FeedModel> likeFeedList(String userID) throws Exception;
	
	List<FeedModel> mainFeedList() throws Exception;
	
	FeedModel getFeed(int feedID) throws Exception;
}
