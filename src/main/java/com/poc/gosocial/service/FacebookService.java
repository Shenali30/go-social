package com.poc.gosocial.service;

import com.poc.gosocial.domain.facebook.Feed;
import com.poc.gosocial.domain.facebook.PostMessage;
import com.poc.gosocial.domain.facebook.PostReply;
import com.poc.gosocial.domain.facebook.Profile;
import org.springframework.http.ResponseEntity;

public interface FacebookService {

    ResponseEntity<Profile> getProfile();

    ResponseEntity<Feed> getFeed();

    ResponseEntity<PostReply> postMessage(PostMessage message);
}
