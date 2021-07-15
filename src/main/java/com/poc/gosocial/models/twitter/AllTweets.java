package com.poc.gosocial.models.twitter;

import lombok.Data;

import java.util.List;

@Data
public class AllTweets {
    private List<Tweet> data;
}
