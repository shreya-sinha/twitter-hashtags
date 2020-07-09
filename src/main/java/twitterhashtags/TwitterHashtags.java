package twitterhashtags;

public class TwitterHashtags {

    public static void main(String[] args) {

        // the reset functionality allows to clear all data and start afresh
        // it is controlled with the reset flag
        boolean reset = true;
        while (reset) {
            // start the main application and processing of tweets
            Twitter twitter = new Twitter();
            reset = twitter.start();
        }
    }

}
