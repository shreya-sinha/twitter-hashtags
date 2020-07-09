package twitterhashtags.service;

import twitterhashtags.cli.Commands;
import twitterhashtags.entity.Tweet;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for printing to console.
 */
public class PrintService {

    /**
     * Guide to navigate through the program.
     */
    public void printManual() {
        System.out.println("=====================================================");
        System.out.println("Keep writing tweets, each on a separate line.");
        Commands[] allCommands = Commands.values();
        for (Commands command : allCommands) {
            System.out.println("Use " + command.getCommand() + command.getManual());
        }
        System.out.println("=====================================================");
    }

    /**
     * Print the output of trending tags with rank and count.
     * @param sortedTags
     */
    public void tagsWithCount(LinkedHashMap<String, Integer> sortedTags) {
        int i=1;
        for (Map.Entry<String, Integer> tag: sortedTags.entrySet()) {
            System.out.println("Rank: " + i++ + "\tCount: " + tag.getValue() + "\tTag: " + tag.getKey());
        }
        printManual();
    }

    /**
     * Print all tweets. Prints array of tags in the tweet, "NO TAGS" if there are none.
     * @param allTweets
     */
    public void allTweets(List<Tweet> allTweets) {
        for (Tweet tweet: allTweets) {
            System.out.println(tweet.getText());
            System.out.println("\t"+(tweet.getHashtags().isPresent()? tweet.getHashtags().get():"NO TAGS"));
        }
        printManual();
    }
}
