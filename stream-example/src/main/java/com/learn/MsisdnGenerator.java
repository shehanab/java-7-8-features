package com.learn;

import java.util.*;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by Shehan on 5/29/15.
 */
public class MsisdnGenerator {

    private static final String NUMBER_PATTERN = "^[0-9]+$";
    private static final String RANGE_PATTERN = "^[0-9]+-[0-9]+";
    private static final String WILDCARD_PATTERN = "[0-9]+\\*";
    public static final int MSISDN_LENGTH = 11;
    private List<String> pattern;

    public MsisdnGenerator(String pattern) {
        this.pattern = Arrays.asList(pattern.split(","));
    }

    public LongStream stream() {
        return Stream.generate(pattern::stream)
                .flatMap(Function.<Stream<String>>identity())
                .flatMapToLong(this::numberToLong);
    }

    private LongStream numberToLong(String text) {
        String s = text.trim();
        if (s.matches(NUMBER_PATTERN)) {
            return singleEntryStream(s);
        } else if (s.matches(RANGE_PATTERN)) {
            return rangeStream(s);
        } else if (s.matches(WILDCARD_PATTERN)) {
            return wildcardStream(s);
        }
        return LongStream.empty();
    }

    private LongStream singleEntryStream(String s) {
        return LongStream.of(Long.parseLong(s));
    }

    private LongStream rangeStream(String s) {
        String[] strings = s.split("-");
        long start = Long.parseLong(strings[0]);
        long end = Long.parseLong(strings[1]);
        if (start < end) {
            return LongStream.range(start, end + 1);
        } else return LongStream.iterate(start, l -> l - 1).limit(start - end + 1);
    }

    private LongStream wildcardStream(String s) {
        String prefix = s.replace("*", "");
        int wildcardLength = MSISDN_LENGTH - prefix.length();
        String start = prefix + new String(new char[wildcardLength]).replace("\0", "0");
        String end = prefix + new String(new char[wildcardLength]).replace("\0", "9");
        return LongStream.range(Long.parseLong(start), Long.parseLong(end) + 1);
    }
}
