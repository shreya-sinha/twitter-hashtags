package twitterhashtags.service;

import twitterhashtags.storage.Data;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 */
public class HashtagService {

    private static final String TAG_REGEX = "^(#[\\w_]+)|\\s(#[\\w_]+)";
    private Data storage = Data.getInstance();

    public Optional<List<String>> findTags(String tweetText) {
        List<String> hashtags = new ArrayList<>();
        Pattern tagPattern = Pattern.compile(TAG_REGEX);
        Matcher matcher = tagPattern.matcher(tweetText);
        while (matcher.find())
        {
            if(matcher.group(1) != null) {
                hashtags.add(matcher.group(1));
            }
            if(matcher.group(2) != null) {
                hashtags.add(matcher.group(2));
            }

        }
        if (hashtags.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(hashtags);
    }

    public void countTags(Optional<List<String>> tags) {
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

    public LinkedHashMap<String, Integer> getMostPopularNTags(int n) {
        return tagsSortedByCountDesc(n);
    }

    private LinkedHashMap<String, Integer> tagsSortedByCountDesc(int n) {
        return storage.getHashtagsCount().entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
