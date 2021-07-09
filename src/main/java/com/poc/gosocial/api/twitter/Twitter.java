package com.poc.gosocial.api.twitter;

import com.poc.gosocial.api.ApiBinding;
import com.poc.gosocial.api.twitter.models.AllTweets;
import com.poc.gosocial.api.twitter.models.User;
import com.poc.gosocial.api.twitter.models.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


public class Twitter extends ApiBinding {

//    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // using twitter API V2 early access
    private static final String TWITTER_API_BASE_URL_V2 = "https://api.twitter.com/2";

    public Twitter(String bearerToken){
        super(bearerToken);
    }

    public ResponseEntity<AllTweets> searchByKeyword(String queryKeyWord){
        // API V2 only provides recent searches up to 7 days
        String urlForSearchByKeyword = TWITTER_API_BASE_URL_V2+"/tweets/search/recent";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlForSearchByKeyword)
                .queryParam("query", queryKeyWord);

        URI uriSearchByKeyword = URI.create(builder.toUriString());
        AllTweets allTweets = restTemplate.getForObject(uriSearchByKeyword, AllTweets.class);
        return new ResponseEntity<>(allTweets, HttpStatus.OK);

    }

    public ResponseEntity<AllTweets> getTimeline(String username){

        String userId = getUserId(username);

        String urlForGetTimeline = TWITTER_API_BASE_URL_V2+"/users/{id}/tweets";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlForGetTimeline);

        Map<String, String> urlParamsForGetTimeline = new HashMap<>();
        urlParamsForGetTimeline.put("id", userId);

        URI uriGetTimeline = builder.buildAndExpand(urlParamsForGetTimeline).toUri();
        AllTweets timelineTweets = restTemplate.getForObject(uriGetTimeline,AllTweets.class);
        return new ResponseEntity<>(timelineTweets, HttpStatus.OK);
    }

    public String getUserId(String username){
        String urlForGetUserId = TWITTER_API_BASE_URL_V2+"/users/by/username/{username}";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlForGetUserId);

        Map<String, String> urlParamsForGetUserId = new HashMap<>();
        urlParamsForGetUserId.put("username", username);

        URI uriGetUserId = builder.buildAndExpand(urlParamsForGetUserId).toUri();
        User user = restTemplate.getForObject(uriGetUserId, UserInfo.class).getData();
        return user.getId();
    }

}
