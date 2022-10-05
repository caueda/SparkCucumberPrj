package aop;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Guitarist implements Singer {
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