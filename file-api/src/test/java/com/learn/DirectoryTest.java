package com.learn;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Shehan on 5/13/15.
 */
public class DirectoryTest {

    private String resourceDirectory;

    @Before
    public void setUp() throws Exception {

        URL resource = getClass().getClassLoader().getResource("Test.txt");
        Path path = Paths.get(resource.getPath());
        resourceDirectory = path.getParent().toString();

    }
    
    @Test
    public void testCreateDirectory() throws Exception {

        Files.deleteIfExists(Paths.get(resourceDirectory , "test-dir"));
        Files.createDirectory(Paths.get(resourceDirectory , "test-dir"));

    }

    @Test
    public void testListDirContent() throws Exception {

        DirectoryStream<Path> target = Files.newDirectoryStream(Paths.get(resourceDirectory).getParent());
        for (Path path : target) {
            System.out.println("Found : " + path);
        }

    }

    @Test
    public void testFilterContent() throws Exception {

        DirectoryStream<Path> target = Files.newDirectoryStream(Paths.get(resourceDirectory), "*.txt");
        for (Path path : target) {
            System.out.println("Found : " + path);
        }

    }
}
