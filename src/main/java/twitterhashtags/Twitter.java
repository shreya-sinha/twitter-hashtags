package twitterhashtags;

import twitterhashtags.cli.Commands;
import twitterhashtags.entity.Tweet;
import twitterhashtags.service.PrintService;
import twitterhashtags.service.TweetService;
import twitterhashtags.storage.Data;

import java.util.*;

/**
 * Uses the {@link TweetService} for processing tweets.
 * Handles {@link twitterhashtags.cli.Commands} command inputs as well.
 */
public class Twitter {

    private TweetService tweetService;
    private PrintService printService;

    public Twitter() {
        tweetService = new TweetService();
        printService = new PrintService();
    }

    /**
     * Starts processing command line input and handles tweets and commands.
     * @return reset flag - if true execution starts with clean data set,
     *                      if false program quits.
     */
    public boolean start() {

        printService.printManual();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        // keep processing inputs until the $quit command is received
        while(!quit) {
            String input = scanner.nextLine();
            /**
             * {@link Commands} defines all available options.
             *  If input is not a command, it is processed as a tweet.
             */
            if ( ! Commands.getNames().contains(input)) {
                tweetService.processTweet(input);
            } else {
                //Take action according to each command
                switch (input) {
                    case "$eval":
                        //find the 10 most trending hashtags
                        LinkedHashMap<String, Integer> sortedTags = tweetService.getMostPopularNTags(10);
                        printService.tagsWithCount(sortedTags);
                        break;

                    case "$reset":
                        // reset program execution
                        Data.getInstance().reset();
                        return true;

                    case "$quit":
                        // quit
                        quit = true;
                        break;

                    case "$print":
                        // print each tweet with the hashtags identified in it
                        // useful for debugging
                        List<Tweet> allTweets = tweetService.getAllTweets();
                        printService.allTweets(allTweets);
                        break;
                }
            }
        }
        scanner.close();
        // return flag for reset = false, this is to exit the program
        return false;
    }
}
