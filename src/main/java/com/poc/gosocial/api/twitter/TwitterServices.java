package com.poc.gosocial.api.twitter;

import com.poc.gosocial.api.twitter.models.AllTweets;
import org.springframework.http.ResponseEntity;

public interface TwitterServices {

    ResponseEntity<AllTweets> searchByKeyword(String queryKeyWord);

    ResponseEntity<AllTweets> getTimeline(String username);

    String getUserId(String username);
}
