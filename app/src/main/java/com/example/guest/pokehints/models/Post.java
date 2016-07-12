package com.example.guest.pokehints.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Guest on 7/12/16.
 */
@IgnoreExtraProperties
public class Post {

    String username;
    String content;
    List<String> categories = new ArrayList<>();
    String timestamp;

    public Post(){}

    public Post(String username, String content, List<String> categories, String timestamp) {
        this.username = username;
        this.content = content;
        this.categories = categories;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("content", content);
        result.put("categories", categories);
        result.put("timestamp", timestamp);

        return result;
    }

}
