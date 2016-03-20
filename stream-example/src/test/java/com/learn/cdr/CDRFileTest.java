package com.learn.cdr;

import com.learn.cdr.CDRFile;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by Shehan on 6/3/15.
 */
public class CDRFileTest {

    private CDRFile cdrFile;

    @Before
    public void setUp() throws Exception {
        cdrFile = new CDRFile(Paths.get("cdr/cdr-2015-06-10.log"));
    }

    @Test
    public void testRecordCount() throws Exception {
        long count = cdrFile.stream().count();
        System.out.println(count);
    }

    @Test
    public void testDistinctTypes() throws Exception {
        long distinctTypeCount = cdrFile.stream().map(cdr -> cdr[1]).distinct().count();
        System.out.println(distinctTypeCount);

    }

    @Test
    public void testCountByType() throws Exception {
        List<String> strings = cdrFile.stream().filter(cdr -> cdr[1].equals("344"))
                .map(arr -> Arrays.stream(arr).collect(joining(",")))
                .collect(toList());
        strings.forEach(System.out::println);
    }

    @Test
    public void testMapForCounts() throws Exception {
        Map<String, Integer> collected = cdrFile.stream()
                .map(cdr -> cdr[1])
                .reduce(new HashMap<>(),
                        (m, e) -> {
                            m.computeIfPresent(e, (s, i) -> i++);
                            m.computeIfAbsent(e, s -> 1);
                            return m;
                        },
                        (m1, m2) -> m1);
//        System.out.println(collected);

        Map<String, Long> map = cdrFile.stream()
                .map(cdr -> cdr[1])
                .collect(groupingBy(a -> a, counting()));
        System.out.println(map);

    }

    @Test
    public void testGetTransactionIds() throws Exception {
        List<String> collect = cdrFile.stream()
                .filter(cdr -> cdr[1].equals("200") || cdr[1].equals("202"))
                .map(cdr -> cdr[12])
                .collect(toList());
        System.out.println(collect);
    }

    /**
     * Collection session ids.
     * Group by session id.
     *
     *
     *
     * @throws Exception
     */
    @Test
    public void testGroupBySessionAndOrderByEvent() throws Exception {

        Stream<String[]> stream = cdrFile.stream();
        Map<String, List<String[]>> collect = stream
                .collect(groupingBy(cdr -> cdr[cdr.length - 1]));

        collect.forEach((key, list) -> Collections.sort(list, (e1, e2) -> Integer.parseInt(e1[0]) - Integer.parseInt(e2[0])));
    }

    @Test
    public void testGroupBySessionAndOrderByEvent2() throws Exception {
        List<String[]> collect = cdrFile.stream().collect(toList());
        HashMap<String, List<String[]>> result = new HashMap<>();

        for (String[] strings : collect) {
            String sessionId = strings[strings.length - 1];
            List<String[]> list = result.get(sessionId);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(strings);
            result.put(sessionId, list);
        }

        for (List<String[]> strings : result.values()) {
            Collections.sort(strings, (o1, o2) -> Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]));
        }

        System.out.println(result);
    }

    @Test
    public void testAverageSessionDuration() throws Exception {
        Stream<String[]> stream = cdrFile.stream();
        Map<String, List<String[]>> grouped = stream
                .collect(groupingBy(cdr -> cdr[cdr.length - 1]));

        grouped.forEach((key, list) -> Collections.sort(list, (e1, e2) -> Integer.parseInt(e1[0]) - Integer.parseInt(e2[0])));

//        Map<String, Long> result =
//                grouped.entrySet().stream()
//                        .collect(toMap(e -> e.getKey(), e -> calculateDuration(e.getValue())));
//        System.out.println(result);
        Double avarage = grouped.values().stream().collect(Collectors.averagingLong(this::calculateDuration));
        System.out.println(avarage);
    }

    private long calculateDuration(List<String[]> cdrs) {
        try {
            String[] first = cdrs.get(0);
            LocalDateTime firstDate = convertToDate(first[1]);
            String[] last = cdrs.get(cdrs.size() - 1);
            LocalDateTime lastDate = convertToDate(last[1]);
            return Duration.between(firstDate, lastDate).toMillis();
        } catch (ParseException e) {
            return 0;
        }
    }

    private LocalDateTime convertToDate(String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.S");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            try {
                return LocalDateTime.parse(date, formatter1);
            } catch (DateTimeParseException e1) {
                try {
                    return LocalDateTime.parse(date, formatter2);
                } catch (DateTimeParseException e2) {
                    return LocalDateTime.parse(date, formatter3);
                }
            }
        }
    }
}