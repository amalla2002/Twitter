package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import okhttp3.Headers;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    // pass in context and list of tweets
    Context context;
    List<Tweet> tweets;

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }
    // 1 row = inflate
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }
    // bind values based on pos
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get data
        Tweet tweet = tweets.get(position);
        //bind data
        holder.bind(tweet);

    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // define a viewhoolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        ImageView ivMedia; // new
        TextView tvCreatedAt;
        TextView tvName;
        ImageButton ibFavorite;
        TextView tvFavoriteCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            ivMedia = itemView.findViewById(R.id.ivMedia); //new
            tvCreatedAt = itemView.findViewById(R.id.tvCreated);
            tvName = itemView.findViewById(R.id.tvName);
            ibFavorite = itemView.findViewById(R.id.ibFavorite);
            tvFavoriteCount = itemView.findViewById(R.id.tvFavoriteCount);

        }
        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            tvName.setText(tweet.user.name);


            //here it crashes
            tvFavoriteCount.setText(String.valueOf(tweet.favoriteCount));
            if (tweet.isFavorite){
                Drawable newImage = context.getDrawable(R.drawable.ic_vector_heart);
                ibFavorite.setImageDrawable(newImage);
            } else {
                Drawable newImage = context.getDrawable(R.drawable.ic_vector_heart_stroke);
                ibFavorite.setImageDrawable(newImage);
            }

            //

            Glide.with(context).load(tweet.user.publicImageUrl).transform(new RoundedCorners(90)).into(ivProfileImage);
            if (tweet.mediaUrl!="") {
                ivMedia.setVisibility(View.VISIBLE); //
                Glide.with(context).load(tweet.mediaUrl).into(ivMedia); // new
            }
            else {
                ivMedia.setVisibility(View.GONE);
            }
            tvCreatedAt.setText(getRelativeTimeAgo(tweet.createdAt));

            ibFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                     liked //send unlike. lower like count, change icon

                    if (tweet.isFavorite) {
                        tweet.isFavorite = false;
                        TwitterApp.getRestClient(context).unFavorite(tweet.id, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Headers headers, JSON json) {
                                Log.i("adapter", "this should've been liked, go check");
                            }

                            @Override
                            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

                            }
                        });

                        Drawable newImage = context.getDrawable(R.drawable.ic_vector_heart_stroke);
                        --tweet.favoriteCount;
                        ibFavorite.setImageDrawable(newImage);
                        tvFavoriteCount.setText(String.valueOf(tweet.favoriteCount));

                    } else {// not liked // send like, up like count, change icon
                        tweet.isFavorite = true;
                        TwitterApp.getRestClient(context).Favorite(tweet.id, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Headers headers, JSON json) {
                                Log.i("adapter", "this should've been liked, go check");
                            }
                            @Override
                            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

                            }
                        });
                        Drawable newImage = context.getDrawable(R.drawable.ic_vector_heart);
                        ibFavorite.setImageDrawable(newImage);
                        ++tweet.favoriteCount;
                        tvFavoriteCount.setText(String.valueOf(tweet.favoriteCount));

                    }

                }
            });

        }
        //
        // Clean all elements of the recycler
        public void clear() {
            tweets.clear();
            notifyDataSetChanged();
        }

        // Add a list of items -- change to type used
        public void addAll(List<Tweet> list) {
            tweets.addAll(list);
            notifyDataSetChanged();
        }

    }

    //
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        try {
            long time = sf.parse(rawJsonDate).getTime();
            long now = System.currentTimeMillis();

            final long diff = now - time;
            if (diff < MINUTE_MILLIS) {
                return "just now";
            } else if (diff < 2 * MINUTE_MILLIS) {
                return "a minute ago";
            } else if (diff < 50 * MINUTE_MILLIS) {
                return diff / MINUTE_MILLIS + " m";
            } else if (diff < 90 * MINUTE_MILLIS) {
                return "an hour ago";
            } else if (diff < 24 * HOUR_MILLIS) {
                return diff / HOUR_MILLIS + " h";
            } else if (diff < 48 * HOUR_MILLIS) {
                return "yesterday";
            } else {
                return diff / DAY_MILLIS + " d";
            }
        } catch (ParseException e) {
            Log.i("TAG", "getRelativeTimeAgo failed");
            e.printStackTrace();
        }

        return "";
    }

}
