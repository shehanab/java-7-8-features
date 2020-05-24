package com.learn;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Shehan on 5/13/15.
 */
public class IOTest {

    private String resourceDirectory;

    @Before
    public void setUp() throws Exception {

        URL resource = getClass().getClassLoader().getResource("Test.txt");
        Path path = Paths.get(resource.getPath());
        resourceDirectory = path.getParent().toString();

    }

    @Test
    public void testReadFile() throws Exception {
        try (FileReader fileReader = new FileReader(resourceDirectory + "/Test.txt")) {
            int c;
            while ((c = fileReader.read()) != -1) {
                System.out.print((char) c);
            }
        }
    }

    @Test
    public void testReadFile2() throws Exception {
        try (FileReader fileReader = new FileReader(resourceDirectory + "/Test.txt")) {
            for (int c = fileReader.read(); c != -1; c = fileReader.read()) {
                System.out.print((char) c);
            }
        }
    }

    @Test
    public void testWriteToFile() throws Exception {
        String s = "Hello World";
        try (FileWriter fileWriter = new FileWriter(resourceDirectory + "/Test2.txt")) {
            for (char c : s.toCharArray()) {
                fileWriter.write(c);
            }
        }
    }

    @Test
    public void testReadFileToBuffer() throws Exception {
        try (FileReader fileReader = new FileReader(resourceDirectory + File.pathSeparator + "Test.txt");
             BufferedReader reader = new BufferedReader(fileReader)) {
            String s = reader.readLine();
            System.out.println(s);
        }
    }

    @Test
    public void testWriteToFileFromBuffer() throws Exception {
        String s = "Add this values 1 2 3";
        try (FileWriter fileWriter = new FileWriter(resourceDirectory + "/Test3.txt");
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(s);
            writer.flush();
        }
    }

    @Test
    public void testScan() throws Exception {

        try (FileReader fileReader = new FileReader(resourceDirectory + "/Test.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             Scanner scanner = new Scanner(bufferedReader)) {
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        }

    }

    @Test
    public void testScanAdvanced() throws Exception {

        try (FileReader fileReader = new FileReader(resourceDirectory + "/Test3.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             Scanner scanner = new Scanner(bufferedReader)) {

            Double result = 0D;
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    result += scanner.nextInt();
                } else scanner.next();
            }
            System.out.println(result);
        }

    }
}