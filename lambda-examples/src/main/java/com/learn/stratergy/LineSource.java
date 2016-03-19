package com.learn.stratergy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Shehan on 5/25/15.
 */
public class LineSource {

    private Path path;
    private List<LineData> lines;

    public LineSource(Path path) {
        this.path = path;
    }

    public void init() throws IOException {
        List<String> strings = Files.lines(path).collect(toList());
        lines = IntStream.range(0, strings.size())
                .mapToObj(value -> new LineData(strings.get(value), value))
                .collect(toList());
    }

    public List<LineData> grep(String str) {
        ArrayList<LineData> results = new ArrayList<>();
        for (LineData line : lines) {
            if (line.getLine().contains(str)) {
                results.add(line);
            }
        }
        return results;
    }

    public List<LineData> grep(Predicate<LineData> predicate) {
        ArrayList<LineData> lineData = new ArrayList<>();
        for (LineData line : lines) {
            if (predicate.test(line)) {
                lineData.add(line);
            }
        }
        return lineData;
    }

    public class LineData {
        private String line;
        private int lineNumber;

        public LineData(String line, int lineNumber) {
            this.line = line;
            this.lineNumber = lineNumber;
        }

        public String getLine() {
            return line;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        @Override
        public String toString() {
            return lineNumber + "\t" + line;
        }
    }

}
