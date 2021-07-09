package com.poc.gosocial.api.facebook.models;

import lombok.Data;

import java.util.List;

@Data
public class Feed {
    private List<Post> data;
}
