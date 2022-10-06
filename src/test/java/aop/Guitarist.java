package aop;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Guitarist implements Singer {

    private String name;
    private String age;

    public void init() {
        log.info(">>>> Calling init method.");
    }
    @Override
    public String sing() {
        String lyric = "You're gonna live forever in me.";
        log.info(lyric);
        return lyric;
    }
    @Override
    public String sing(String song) {
        return String.format("Singing the song %s", song);
    }
}
