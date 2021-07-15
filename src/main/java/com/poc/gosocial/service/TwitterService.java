package com.poc.gosocial.service;

import com.poc.gosocial.domain.twitter.AllTweets;
import org.springframework.http.ResponseEntity;

public interface TwitterService {

    ResponseEntity<AllTweets> searchByKeyword(String queryKeyWord);

    ResponseEntity<AllTweets> getTimeline(String username);

    String getUserId(String username);
}
