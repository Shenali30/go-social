package com.poc.gosocial.controller;

import com.poc.gosocial.api.twitter.Twitter;
import com.poc.gosocial.api.twitter.models.AllTweets;
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

    private final Twitter twitter;

    @Autowired
    public TwitterController(Twitter twitter) {
        this.twitter = twitter;
    }

    @GetMapping("/search")
    public ResponseEntity<AllTweets> searchByKeyword(@RequestParam("query") String queryKeyWord){
        log.info("API REQUEST: GET [TWITTER] recent tweets for given keyword");
        return twitter.searchByKeyword(queryKeyWord);
    }

    @GetMapping("/timeline")
    public ResponseEntity<AllTweets> getTimeline(@RequestParam("username") String username){
        log.info("API REQUEST: GET [TWITTER] timeline of given username");
        return twitter.getTimeline(username);
    }

}
