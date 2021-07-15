package com.poc.gosocial.models.facebook;

import lombok.Data;

import java.util.List;

@Data
public class Feed {
    private List<Post> data;
}
