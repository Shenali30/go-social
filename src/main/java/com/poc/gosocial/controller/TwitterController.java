package com.poc.gosocial.controller;

import com.poc.gosocial.service.TwitterService;
import com.poc.gosocial.models.twitter.AllTweets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/twitter")
@Slf4j
public class TwitterController {

    @Autowired
    private final TwitterService twitterService;

    public TwitterController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @GetMapping("/search")
    public ResponseEntity<AllTweets> searchByKeyword(@RequestParam(value = "query") String queryKeyWord){
        log.info("[TWITTER] API REQUEST: GET recent tweets for given keyword");
        return twitterService.searchByKeyword(queryKeyWord);
    }

    @GetMapping("/timeline")
    public ResponseEntity<AllTweets> getTimeline(@RequestParam(value = "username") String username){
        log.info("[TWITTER] API REQUEST: GET timeline of given username");
        return twitterService.getTimeline(username);
    }

}
