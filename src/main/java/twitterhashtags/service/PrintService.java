package twitterhashtags.service;

import twitterhashtags.cli.Commands;
import twitterhashtags.entity.Tweet;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PrintService {

    public void printManual() {
        System.out.println("=====================================================");
        System.out.println("Keep writing tweets, each on a separate line.");
        Commands[] allCommands = Commands.values();
        for (Commands command : allCommands) {
            System.out.println("Use " + command.getCommand() + command.getManual());
        }
        System.out.println("=====================================================");
    }

    public void tagsWithCount(LinkedHashMap<String, Integer> sortedTags) {
        int i=1;
        for (Map.Entry<String, Integer> tag: sortedTags.entrySet()) {
            System.out.println("Rank: " + i++ + "\tCount: " + tag.getValue() + "\tTag: " + tag.getKey());
        }
        printManual();
    }

    public void allTweets(List<Tweet> allTweets) {
        for (Tweet tweet: allTweets) {
            System.out.println(tweet.getText());
            System.out.println("\t"+(tweet.getHashtags().isPresent()? tweet.getHashtags().get():"NO TAGS"));
        }
        printManual();
    }
}
