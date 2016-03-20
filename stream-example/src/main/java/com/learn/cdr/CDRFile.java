package com.learn.cdr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by Shehan on 6/3/15.
 */
public class CDRFile {

    private Path path;

    public CDRFile(Path path) {
        this.path = path;
    }

    public Stream<String[]> stream() throws IOException {
        return Files.lines(path).map(s -> s.split(","));
    }
}
