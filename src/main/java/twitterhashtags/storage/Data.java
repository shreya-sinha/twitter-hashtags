package twitterhashtags.storage;

import twitterhashtags.entity.Tweet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Data {

    private List<Tweet> allTweets;
    private Map<String, Integer> hashtagsCount;


    private static volatile Data data;
    private Data() {
        reset();
    }

    public static  Data getInstance() {
        if (data == null) {
            synchronized (Data.class) {
                if(data == null) {
                    data = new Data();
                }
            }
        }
        return data;
    }

    public void reset() {
        allTweets = new LinkedList<>();
        hashtagsCount = new HashMap<>();
    }

    public List<Tweet> getAllTweets() {
        return allTweets;
    }

    public Map<String, Integer> getHashtagsCount() {
        return hashtagsCount;
    }
}
