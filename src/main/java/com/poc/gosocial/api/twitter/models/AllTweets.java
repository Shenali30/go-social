package com.poc.gosocial.api.twitter.models;

import lombok.Data;

import java.util.List;

@Data
public class AllTweets {
    private List<Tweet> data;
}
