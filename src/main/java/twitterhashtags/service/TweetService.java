package twitterhashtags.service;

import twitterhashtags.entity.Tweet;
import twitterhashtags.storage.Data;

import java.util.*;

/**
 * Processes a tweet to find out the hashtags it contains.
 * Uses the {@link HashtagService} for operations on tags.
 */
public class TweetService {

    private HashtagService hashtagService;
    private Data storage;

    /**
     * initialize the {@link HashtagService}
     * storage is a Singleton instance of {@link Data}, the in memory storage.
     */
    public TweetService() {
        hashtagService = new HashtagService();
        storage = Data.getInstance();
    }

    /**
     * Fetch all tweets from storage.
     * @return List of tweets. Empty in the beggining.
     */
    public List<Tweet> getAllTweets() {
        return storage.getAllTweets();
    }

    /**
     * Processes a single tweet to identify the hashtags it contains.
     * Stores the tweet as well as the tags in {@link Data} storage.
     * @param tweetText This is the tweet as received in input.
     */
    public void processTweet(String tweetText) {
        Optional<List<String>> hashtags =  hashtagService.findTags(tweetText);
        Tweet tweet = Tweet.builder()
                .text(tweetText)
                .hashtags(hashtags)
                .build();
        storage.getAllTweets().add(tweet);
        hashtagService.countHashtags(hashtags);
    }

    /**
     * Fetch the list of top n hashtags.
     * @param n The number of hashtags required
     * @return Map of hashtags as keys and their count as values.
     *         This is a LinkedHashMap so that the order is maintained.
     */
    public LinkedHashMap<String, Integer> getMostPopularNTags(int n) {
        return hashtagService.getMostPopularNTags(10);
    }
}
