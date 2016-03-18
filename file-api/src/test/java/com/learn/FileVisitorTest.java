package com.learn;

import com.learn.FileLogger;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

import static org.junit.Assert.*;

/**
 * Created by Shehan on 5/13/15.
 */
public class FileVisitorTest {

    private String resourceDirectory;

    @Before
    public void setUp() throws Exception {

        URL resource = getClass().getClassLoader().getResource("Test.txt");
        Path path = Paths.get(resource.getPath());
        resourceDirectory = path.getParent().toString();

    }

    @Test
    public void testRunFileVisitor() throws Exception {

        Path target = Paths.get(resourceDirectory).getParent();
        Files.walkFileTree(target, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new FileLogger());

    }
}