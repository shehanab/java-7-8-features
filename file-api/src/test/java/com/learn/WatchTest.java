package com.learn;

import com.learn.WatchDirectory;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Shehan on 5/13/15.
 */
public class WatchTest {

    private String resourceDirectory;

    @Before
    public void setUp() throws Exception {

        URL resource = getClass().getClassLoader().getResource("Test.txt");
        Path path = Paths.get(resource.getPath());
        resourceDirectory = path.getParent().toString();

    }

    @Test
    public void testWatching() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try (WatchDirectory watchDIrectory = new WatchDirectory(Paths.get(resourceDirectory));
             WatchDirectory watchDIrectory2 = new WatchDirectory(Paths.get(resourceDirectory))) {
            watchDIrectory.init();
            executorService.submit(watchDIrectory);

            watchDIrectory2.init();
            executorService.submit(watchDIrectory2);
            Thread.sleep(60000);

            executorService.shutdown();
        }

    }
}
