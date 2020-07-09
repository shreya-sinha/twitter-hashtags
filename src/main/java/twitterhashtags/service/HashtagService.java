package twitterhashtags.service;

import twitterhashtags.storage.Data;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Processing for hashtags is done in this class.
 */
public class HashtagService {

    /*
     * The regular expression has two parts:
     * ^(#[\w_]+) matches a hashtag in the beginning of the tweet.
     * \s(#[\w_]+) matches a hasgtag that follows a space character.
     *
     * It allows alphabets, numbers and _
     * A # in the middle of a string, or only # characters do not count as a hashtags.
     * Assumptions for a valid hashtag are made according to:
     * https://help.twitter.com/en/using-twitter/how-to-use-hashtags Tips for using hashtags
     */
    private static final String TAG_REGEX = "^(#[\\w_]+)|\\s(#[\\w_]+)";
    private Data storage = Data.getInstance();

    public Optional<List<String>> findTags(String tweetText) {
        List<String> hashtags = new ArrayList<>();
        Pattern tagPattern = Pattern.compile(TAG_REGEX);
        Matcher matcher = tagPattern.matcher(tweetText);
        while (matcher.find())
        {
            // matches hashtags in the beginning of tweet
            if(matcher.group(1) != null) {
                hashtags.add(matcher.group(1));
            }
            // matches hashtags in any other part of tweet
            if(matcher.group(2) != null) {
                hashtags.add(matcher.group(2));
            }

        }
        if (hashtags.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(hashtags);
    }

    /**
     * Count the occurrence of each hashtag and save in storage.
     * @param tags List of hashtags in a tweet.
     */
    public void countHashtags(Optional<List<String>> tags) {
        Map<String, Integer> tagCount = storage.getHashtagsCount();
        if (tags.isPresent()) {
            for (String tag : tags.get()) {
                Integer count = 0;
                if (tagCount.containsKey(tag)) {
                    count = tagCount.get(tag);
                }
                tagCount.put(tag, ++count);
            }
        }
    }

    /**
     * Get the top n trending hashtags.
     * @param n No. of top used hashtags needed.
     * @return Map of hashtags as key and their count as value.
     *         LinkedHashMap is user to preserve order.
     */
    public LinkedHashMap<String, Integer> getMostPopularNTags(int n) {
        return tagsSortedByCountDesc(n);
    }

    /**
     * Streams the Map of all hashtags stored with their counts,
     * Sorts them in descending order according to value,
     * Upto a max of n hashtags,
     * and returns them in a LinkedHashMap
     * @param n Number of trending hashtags to be found.
     * @return Map of n hashtags sorted by value.
     *         LinkedHashMap is user to preserve order.
     */
    private LinkedHashMap<String, Integer> tagsSortedByCountDesc(int n) {
        return storage.getHashtagsCount().entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
