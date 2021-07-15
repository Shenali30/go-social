package com.poc.gosocial.domain.facebook;

import lombok.Data;

import java.util.List;

@Data
public class Feed {
    private List<Post> data;
}
