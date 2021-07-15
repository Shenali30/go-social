package com.poc.gosocial.service.impl;

import com.poc.gosocial.api.ApiBinding;
import com.poc.gosocial.models.twitter.AllTweets;
import com.poc.gosocial.models.twitter.User;
import com.poc.gosocial.models.twitter.UserInfo;
import com.poc.gosocial.service.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class TwitterServiceImpl extends ApiBinding implements TwitterService {

    // using twitter API V2 early access
    private static final String TWITTER_API_BASE_URL_V2 = "https://api.twitter.com/2";

    public TwitterServiceImpl(String bearerToken){
        super(bearerToken);
    }

    @Override
    public ResponseEntity<AllTweets> searchByKeyword(String queryKeyWord){
        // API V2 only provides recent searches up to 7 days

        String urlForSearchByKeyword = TWITTER_API_BASE_URL_V2+"/tweets/search/recent";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlForSearchByKeyword)
                .queryParam("query", queryKeyWord);

        URI uriForSearchByKeyword = URI.create(builder.toUriString());

        log.info("[TWITTER] Calling {} endpoint", uriForSearchByKeyword);
        AllTweets allTweets = restTemplate.getForObject(uriForSearchByKeyword, AllTweets.class);
        log.info("[TWITTER] API RESPONSE: Returning response");

        return new ResponseEntity<>(allTweets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AllTweets> getTimeline(String username){

        log.info("[TWITTER] GET id of given username");
        String userId = getUserId(username);

        String urlForGetTimeline = TWITTER_API_BASE_URL_V2+"/users/{id}/tweets";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlForGetTimeline);

        Map<String, String> urlParamsForGetTimeline = new HashMap<>();
        urlParamsForGetTimeline.put("id", userId);

        URI uriForGetTimeline = builder.buildAndExpand(urlParamsForGetTimeline).toUri();

        log.info("[TWITTER] Calling {} endpoint", uriForGetTimeline);
        AllTweets timelineTweets = restTemplate.getForObject(uriForGetTimeline,AllTweets.class);
        log.info("[TWITTER] API RESPONSE: Returning response");

        return new ResponseEntity<>(timelineTweets, HttpStatus.OK);
    }

    @Override
    public String getUserId(String username){
        String urlForGetUserId = TWITTER_API_BASE_URL_V2+"/users/by/username/{username}";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlForGetUserId);

        Map<String, String> urlParamsForGetUserId = new HashMap<>();
        urlParamsForGetUserId.put("username", username);

        URI uriForGetUserId = builder.buildAndExpand(urlParamsForGetUserId).toUri();

        log.info("[TWITTER] Calling {} endpoint", uriForGetUserId);
        User user = Objects.requireNonNull(restTemplate.getForObject(uriForGetUserId, UserInfo.class)).getData();
        log.info("[TWITTER] Returning id of given username");

        return user.getId();
    }

}
