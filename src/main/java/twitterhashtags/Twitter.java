package twitterhashtags;

import twitterhashtags.cli.Commands;
import twitterhashtags.entity.Tweet;
import twitterhashtags.service.PrintService;
import twitterhashtags.service.TweetService;
import twitterhashtags.storage.Data;

import java.util.*;

public class Twitter {

    private TweetService tweetService;
    private PrintService printService;

    public Twitter() {
        tweetService = new TweetService();
        printService = new PrintService();
    }

    public boolean start() {

        printService.printManual();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while(!quit) {
            String input = scanner.nextLine();
            if ( ! Commands.getNames().contains(input)) {
                tweetService.processTweet(input);
            } else {
                switch (input) {
                    case "$eval":
                        LinkedHashMap<String, Integer> sortedTags = tweetService.getMostPopularNTags(10);
                        printService.tagsWithCount(sortedTags);
                        break;

                    case "$reset":
                        Data.getInstance().reset();
                        return true;

                    case "$quit":
                        quit = true;
                        break;

                    case "$print":
                        List<Tweet> allTweets = tweetService.getAllTweets();
                        printService.allTweets(allTweets);
                        break;
                }
            }
        }
        scanner.close();
        return false;
    }
}
