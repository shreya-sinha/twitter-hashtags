package twitterhashtags.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@Builder
public class Tweet {

    private String text;
    private Optional<List<String>> hashtags;

}
