package com.poc.gosocial.domain.twitter;

import lombok.Data;

import java.util.List;

@Data
public class AllTweets {
    private List<Tweet> data;
}
