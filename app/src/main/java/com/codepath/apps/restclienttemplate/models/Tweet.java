package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {

    public String body;
    public String createdAt;
    public User user;
    public String mediaUrl; // new
    public String name;
    public Boolean isFavorite;
    public int favoriteCount;
    public String id;
    public Boolean isRetweeted;
    public int retweetCount;
    // reply count is not in the api. : https://developer.twitter.com/en/docs/twitter-api/v1/tweets/timelines/api-reference/get-statuses-home_timeline

    public Tweet() {}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("retweeted_status"))return null;


        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.isFavorite = jsonObject.getBoolean("favorited");
        tweet.favoriteCount = jsonObject.getInt("favorite_count");
        tweet.isRetweeted = jsonObject.getBoolean("retweeted");
        tweet.id = jsonObject.getString("id_str");
        tweet.retweetCount = jsonObject.getInt("retweet_count"); // new

        Log.d("something", jsonObject.toString());
        try {
            tweet.mediaUrl = jsonObject.getJSONObject("entities").getJSONArray("media").getJSONObject(0).getString("media_url_https"); //new
        } catch(Exception e) {
            Log.i("Tweet", "No Image");
        }

        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i<jsonArray.length(); ++i) {
            // dont add retweets!
            Tweet newTweet = fromJson(jsonArray.getJSONObject(i));
            if (newTweet!=null) tweets.add(newTweet);
        }
        return tweets;
    }
}
