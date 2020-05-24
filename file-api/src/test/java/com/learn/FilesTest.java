package com.learn;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Shehan on 5/13/15.
 */
public class FilesTest {

    private String resourceDirectory;

    @Before
    public void setUp() throws Exception {

        URL resource = getClass().getClassLoader().getResource("Test.txt");
        Path path = Paths.get(resource.getPath());
        resourceDirectory = path.getParent().toString();

    }

    @Test
    public void testFileExists() throws Exception {

        assertTrue(Files.exists(Paths.get(resourceDirectory)));
        assertTrue(Files.exists(Paths.get(resourceDirectory, "Test.txt")));
        assertFalse(Files.exists(Paths.get(resourceDirectory, "Test.a")));
        assertTrue(Files.isSameFile(Paths.get(resourceDirectory, "Test.txt"), Paths.get(resourceDirectory, "./Test.txt")));
    }

    @Test
    public void testFileDelete() {

        Path source = null;
        try {
            source = Paths.get(resourceDirectory, "fileForDelete.a");
            Files.createFile(source);
            assertTrue(Files.exists(source));
            Files.delete(source);
            assertFalse(Files.exists(source));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Files.deleteIfExists(source);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testFileCopy() throws Exception {

        Path source = Paths.get(resourceDirectory, "fileForMove.a");
        Path target = Paths.get(resourceDirectory, "../fileForMove.a");
        try {
            Files.createFile(source);
            assertTrue(Files.exists(source));
            Files.copy(source, target);
            assertTrue(Files.exists(source));
            assertTrue(Files.exists(target));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Files.deleteIfExists(source);
            Files.deleteIfExists(target);
        }

    }

    @Test
    public void testFileMove() throws Exception {

        Path source = Paths.get(resourceDirectory, "fileForMove.a");
        Path target = Paths.get(resourceDirectory, "../fileForMove.a");
        try {
            Files.createFile(source);
            assertTrue(Files.exists(source));
            Files.move(source, target);
            assertFalse(Files.exists(source));
            assertTrue(Files.exists(target));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Files.deleteIfExists(source);
            Files.deleteIfExists(target);
        }

    }

    @Test
    public void testFileMeta() throws Exception {

        assertTrue(Files.isDirectory(Paths.get(resourceDirectory)));
        Path testFilePath = Paths.get(resourceDirectory, "Test.txt");
        assertFalse(Files.isDirectory(testFilePath));
        System.out.println("File size : " + Files.size(testFilePath));
        System.out.println("Is regular file : " + Files.isRegularFile(testFilePath));
        System.out.println("Is symbolic link : " + Files.isSymbolicLink(testFilePath));
        System.out.println("Is Hidden : " + Files.isHidden(testFilePath));
        System.out.println("Last modified time : " + Files.getLastModifiedTime(testFilePath));
        System.out.println("Owner : " + Files.getOwner(testFilePath));
        System.out.println("Permissions : " + Files.getPosixFilePermissions(testFilePath));

    }

    @Test
    public void testReadAttributes() throws Exception {
        BasicFileAttributes attr = Files.readAttributes(Paths.get(resourceDirectory, "Test.txt"), BasicFileAttributes.class);

        System.out.println("creationTime: " + attr.creationTime());
        System.out.println("lastAccessTime: " + attr.lastAccessTime());
        System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

        System.out.println("isDirectory: " + attr.isDirectory());
        System.out.println("isOther: " + attr.isOther());
        System.out.println("isRegularFile: " + attr.isRegularFile());
        System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
        System.out.println("size: " + attr.size());
    }

    @Test
    public void testFileStore() throws Exception {

        FileStore fileStore = Files.getFileStore(Paths.get(resourceDirectory, "Test.txt"));
        System.out.println("Store name : " + fileStore.name());
        System.out.println("Total space : " + fileStore.getTotalSpace());
        System.out.println("Usable space : " + fileStore.getUsableSpace());
        System.out.println("Used space : " + (fileStore.getTotalSpace() - fileStore.getUnallocatedSpace()));

    }

    @Test
    public void testRAF() throws Exception {

        Path path = Paths.get(resourceDirectory, "Test4.txt");
        Files.deleteIfExists(path);
        Files.createFile(path);
        try (FileChannel fc = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
            fc.write(ByteBuffer.wrap("Echo this".getBytes()));

            ByteBuffer byteBuffer = ByteBuffer.allocate(20);

            fc.position(5);
            int c;
            do {
                c = fc.read(byteBuffer);
            } while (c != -1 && byteBuffer.hasRemaining());

            System.out.println(new String(byteBuffer.array()));
        }

    }
}
