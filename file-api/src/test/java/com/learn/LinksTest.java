package com.learn;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * Created by Shehan on 5/13/15.
 */
public class LinksTest {

    private String resourceDirectory;

    @Before
    public void setUp() throws Exception {

        URL resource = getClass().getClassLoader().getResource("Test.txt");
        Path path = Paths.get(resource.getPath());
        resourceDirectory = path.getParent().toString();

    }

    @Test
    public void testCreateLink() throws Exception {

        Path source = Paths.get(resourceDirectory, "Test.txt");
        Path target = Paths.get(resourceDirectory, "Test.lnk");

        Files.deleteIfExists(target);
        Files.createSymbolicLink(target, source);

        assertTrue(Files.isSymbolicLink(target));

        System.out.println("Target of the symbolic link '" + target + "' is '" + Files.readSymbolicLink(target) + "'");
    }


}
