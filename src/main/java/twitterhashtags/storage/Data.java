package twitterhashtags.storage;

import lombok.Getter;
import twitterhashtags.entity.Tweet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Singleton class, acts as an in memory database.
 * Stores all tweets and count of each hashtag.
 */
public class Data {

    @Getter
    private List<Tweet> allTweets;
    @Getter
    private Map<String, Integer> hashtagsCount;


    private static volatile Data data;
    private Data() {
        reset();
    }

    public void reset() {
        allTweets = new LinkedList<>();
        hashtagsCount = new HashMap<>();
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

}
