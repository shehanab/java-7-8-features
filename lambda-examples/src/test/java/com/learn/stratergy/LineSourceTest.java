package com.learn.stratergy;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/**
 * Created by Shehan on 5/25/15.
 */
public class LineSourceTest {

    private LineSource file;

    @Before
    public void setUp() throws Exception {
        String path = getClass().getClassLoader().getResource("test.txt").getPath();
        file = new LineSource(Paths.get(path));
        file.init();
    }

    @Test
    public void testGrep() throws Exception {
        List<LineSource.LineData> lines = file.grep("Yossarian");
        lines.forEach(System.out::println);
    }

    @Test
    public void testGrep1() throws Exception {
        Predicate<LineSource.LineData> lineNumberLimiter = getNumberLimiter(50);
        Predicate<LineSource.LineData> caseInsensitiveSearch = getInsensitiveMatcher("Yossarian");

        IntFunction<Predicate<LineSource.LineData>> function = this::getNumberLimiter;
        function.apply(50);

        List<LineSource.LineData> lines = file
                .grep(lineNumberLimiter.and(caseInsensitiveSearch));
        lines.forEach(System.out::println);
    }

    private Predicate<LineSource.LineData> getNumberLimiter(int limit) {
        return lineData -> lineData.getLineNumber() < limit;
    }

    private Predicate<LineSource.LineData> getInsensitiveMatcher(String text) {
        return lineData -> lineData.getLine().toLowerCase().contains(text.toLowerCase());
    }
}