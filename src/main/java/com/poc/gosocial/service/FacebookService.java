package com.poc.gosocial.service;

import com.poc.gosocial.models.facebook.Feed;
import com.poc.gosocial.models.facebook.PostMessage;
import com.poc.gosocial.models.facebook.PostReply;
import com.poc.gosocial.models.facebook.Profile;
import org.springframework.http.ResponseEntity;

public interface FacebookService {

    ResponseEntity<Profile> getProfile();

    ResponseEntity<Feed> getFeed();

    ResponseEntity<PostReply> postMessage(PostMessage message);
}
