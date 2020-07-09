package twitterhashtags;

public class TwitterHashtags {

    public static void main(String[] args) {

        boolean reset = true;
        while (reset) {
            Twitter twitter = new Twitter();
            reset = twitter.start();
        }
    }

}
