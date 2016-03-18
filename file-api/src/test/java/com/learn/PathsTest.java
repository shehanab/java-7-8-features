package com.learn;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by Shehan on 5/13/15.
 */
public class PathsTest {

    private String resourceDirectory;

    @Before
    public void setUp() throws Exception {

        URL resource = getClass().getClassLoader().getResource("Test.txt");
        Path path = Paths.get(resource.getPath());
        resourceDirectory = path.getParent().toString();

    }

    @Test
    public void testPaths() throws Exception {

        Path path = Paths.get(resourceDirectory, "Test.txt");
        assertTrue(path.isAbsolute());
        assertEquals("Test.txt", path.getFileName().toString());
        System.out.println("Name count : " + path.getNameCount());
        for (int i = 0; i < path.getNameCount(); i++) {
            if (i == 0) System.out.println(path.getName(0));
            else System.out.println(new String(new char[i * 2]).replace("\0", " ") + "↳ " + path.getName(i));
        }

        assertEquals(resourceDirectory, path.getParent().toString());

        System.out.println(path.getRoot());
    }

    @Test
    public void testCreateRelativePath() throws Exception {

        Path path = Paths.get(resourceDirectory, "Test.txt");

        Path resolve = path.getParent().getParent().resolve("test-classes");
        System.out.println(path.relativize(resolve));
    }

    @Test
    public void testPathNormalizeAndCompare() throws Exception {

        Path tmp = Paths.get("/tmp");
        Path tmp2 = Paths.get("/home/../tmp");
        assertNotEquals(tmp, tmp2);
        System.out.println(tmp2.normalize());
        assertTrue(tmp2.normalize().equals(tmp));

    }

    @Test
    public void testGetVarargsTest() throws Exception {

        Path path = Paths.get("/", "home", "Shehan", "Documents");
        System.out.println(path.getRoot());

    }
}