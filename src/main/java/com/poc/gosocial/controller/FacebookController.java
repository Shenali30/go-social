package com.poc.gosocial.controller;

import com.poc.gosocial.service.FacebookService;
import com.poc.gosocial.models.facebook.Feed;
import com.poc.gosocial.models.facebook.PostMessage;
import com.poc.gosocial.models.facebook.PostReply;
import com.poc.gosocial.models.facebook.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facebook")
@Slf4j
public class FacebookController {

    @Autowired
    private final FacebookService facebookService;

    public FacebookController(FacebookService facebookService){
        this.facebookService = facebookService;
    }

    @GetMapping("/profile")
    public ResponseEntity<Profile> getPageProfile(){
        log.info("[FACEBOOK] API REQUEST: GET page profile");
        return facebookService.getProfile();
    }

    @GetMapping("/feed")
    public ResponseEntity<Feed> getPageFeed(){
        log.info("[FACEBOOK] API REQUEST: GET page feed");
        return facebookService.getFeed();
    }

    @PostMapping("/post")
    public ResponseEntity<PostReply> postToFeed(@RequestBody PostMessage message){
        log.info("[FACEBOOK] API REQUEST: POST message to feed");
        return facebookService.postMessage(message);
    }
}
