package twitterhashtags.cli;

import java.util.ArrayList;
import java.util.List;

public enum Commands {

    EVALUATE("$eval", " to evaluate most frequent tags"),
    QUIT("$quit", " to quit"),
    RESET("$reset", " to reset / clear all tweets"),
    PRINT("$print", " to print all tweets along with tags");

    private String command;
    private String manual;

    Commands(String command, String manual) {
        this.command = command;
        this.manual = manual;
    }

    public String getCommand() {
        return this.command;
    }
    public String getManual() {
        return this.manual;
    }

    public static List<String> getNames() {
        Commands[] values = Commands.values();
        List<String> names = new ArrayList<>();
        for (Commands c : values) {
           names.add(c.getCommand());
        }
        return names;
    }
}
