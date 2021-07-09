package com.poc.gosocial.controller;

import com.poc.gosocial.api.facebook.Facebook;
import com.poc.gosocial.api.facebook.models.Feed;
import com.poc.gosocial.api.facebook.models.PostMessage;
import com.poc.gosocial.api.facebook.models.PostReply;
import com.poc.gosocial.api.facebook.models.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facebook")
@Slf4j
public class FacebookController {

    private final Facebook facebook;

    @Autowired
    public FacebookController(Facebook facebook){
        this.facebook = facebook;
    }

    @GetMapping("/profile")
    public ResponseEntity<Profile> getPageProfile(){
        log.info("API REQUEST: GET [FACEBOOK] page profile");
        return facebook.getProfile();
    }

    @GetMapping("/feed")
    public ResponseEntity<Feed> getPageFeed(){
        log.info("API REQUEST: GET [FACEBOOK] page feed");
        return facebook.getFeed();
    }

    @PostMapping("/post")
    public ResponseEntity<PostReply> postToFeed(@RequestBody PostMessage message){
        log.info("API REQUEST: POST [FACEBOOK] message to feed");
        return facebook.postMessage(message);
    }
}
