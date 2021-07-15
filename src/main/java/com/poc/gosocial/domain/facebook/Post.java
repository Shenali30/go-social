package com.poc.gosocial.domain.facebook;

import lombok.Data;

@Data
public class Post {
    private String id;
    private String message;
    private String created_time;
}
