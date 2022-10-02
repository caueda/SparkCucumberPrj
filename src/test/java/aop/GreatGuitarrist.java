package aop;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreatGuitarrist implements Singer {
    @Override
    public String sing() {
        String lyric = "Great Guitarist. You're gonna live forever in me.";
        log.info(lyric);
        return lyric;
    }
}
