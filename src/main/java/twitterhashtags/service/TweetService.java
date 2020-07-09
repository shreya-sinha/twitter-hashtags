package twitterhashtags.service;

import twitterhashtags.entity.Tweet;
import twitterhashtags.storage.Data;

import java.util.*;

public class TweetService {

    private HashtagService hashtagService;
    private Data storage;

    public TweetService() {
        hashtagService = new HashtagService();
        storage = Data.getInstance();
    }

    public List<Tweet> getAllTweets() {
        return storage.getAllTweets();
    }

    public void processTweet(String tweetText) {
        Optional<List<String>> hashtags =  hashtagService.findTags(tweetText);
        Tweet tweet = Tweet.builder()
                .text(tweetText)
                .hashtags(hashtags)
                .build();
        storage.getAllTweets().add(tweet);
        hashtagService.countTags(hashtags);
    }

    public LinkedHashMap<String, Integer> getMostPopularNTags(int n) {
        return hashtagService.getMostPopularNTags(10);
    }
}
