package com.poc.gosocial.service.impl;

import com.poc.gosocial.api.ApiBinding;
import com.poc.gosocial.models.facebook.Feed;
import com.poc.gosocial.models.facebook.PostMessage;
import com.poc.gosocial.models.facebook.PostReply;
import com.poc.gosocial.models.facebook.Profile;
import com.poc.gosocial.service.FacebookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
public class FacebookServiceImpl extends ApiBinding implements FacebookService {

    private static final String FB_GRAPH_API_BASE_URL = "https://graph.facebook.com/v11.0";

    public FacebookServiceImpl(String pageAccessToken) {
        super(pageAccessToken);
    }

    @Override
    public ResponseEntity<Profile> getProfile(){

        String urlForGetProfile = FB_GRAPH_API_BASE_URL+"/me";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlForGetProfile);
        URI uriForGetProfile = URI.create(builder.toUriString());

        log.info("[FACEBOOK] Calling {} endpoint", uriForGetProfile);
        Profile profile = restTemplate.getForObject(uriForGetProfile,Profile.class);
        log.info("[FACEBOOK] API RESPONSE: Returning response");

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Feed> getFeed(){

        String urlForGetFeed = FB_GRAPH_API_BASE_URL+"/me/feed";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlForGetFeed);
        URI uriForGetFeed = URI.create(builder.toUriString());

        log.info("[FACEBOOK] Calling {} endpoint", uriForGetFeed);
        Feed feed = restTemplate.getForObject(uriForGetFeed, Feed.class);
        log.info("[FACEBOOK] API RESPONSE: Returning response");

        return new ResponseEntity<>(feed, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostReply> postMessage(PostMessage message){

        String urlForPostMessage = FB_GRAPH_API_BASE_URL+"/me/feed";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlForPostMessage);
        URI uriForPostMessage = URI.create(builder.toUriString());

        log.info("[FACEBOOK] Calling {} endpoint", uriForPostMessage);
        PostReply postReply = restTemplate.postForObject(uriForPostMessage,message,PostReply.class);
        log.info("[FACEBOOK] API RESPONSE: Returning response");

        return new ResponseEntity<>(postReply, HttpStatus.OK);
    }

}