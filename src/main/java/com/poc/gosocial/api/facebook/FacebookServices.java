package com.poc.gosocial.api.facebook;

import com.poc.gosocial.api.facebook.models.Feed;
import com.poc.gosocial.api.facebook.models.PostMessage;
import com.poc.gosocial.api.facebook.models.PostReply;
import com.poc.gosocial.api.facebook.models.Profile;
import org.springframework.http.ResponseEntity;

public interface FacebookServices {

    ResponseEntity<Profile> getProfile();

    ResponseEntity<Feed> getFeed();

    ResponseEntity<PostReply> postMessage(PostMessage message);
}
