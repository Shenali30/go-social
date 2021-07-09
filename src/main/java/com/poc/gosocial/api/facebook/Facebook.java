package com.poc.gosocial.api.facebook;

import com.poc.gosocial.api.ApiBinding;
import com.poc.gosocial.api.facebook.models.Feed;
import com.poc.gosocial.api.facebook.models.PostMessage;
import com.poc.gosocial.api.facebook.models.PostReply;
import com.poc.gosocial.api.facebook.models.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Facebook extends ApiBinding {

    private static final String FB_GRAPH_API_BASE_URL = "https://graph.facebook.com/v11.0";

    public Facebook(String pageAccessToken) {
        super(pageAccessToken);
    }

    public ResponseEntity<Profile> getProfile(){
        Profile profile = restTemplate.getForObject(FB_GRAPH_API_BASE_URL+"/me",Profile.class);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    public ResponseEntity<Feed> getFeed(){
        Feed feed = restTemplate.getForObject(FB_GRAPH_API_BASE_URL+"/me/feed", Feed.class);
        return new ResponseEntity<>(feed, HttpStatus.OK);
    }

    public ResponseEntity<PostReply> postMessage(PostMessage message){
        PostReply postReply = restTemplate.postForObject(FB_GRAPH_API_BASE_URL+"/me/feed",message,PostReply.class);
        return new ResponseEntity<>(postReply, HttpStatus.OK);
    }

}