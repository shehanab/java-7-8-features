package com.learn;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created by Shehan on 5/13/15.
 */
public class WatchDirectory implements Runnable, Closeable {

    private WatchService watchService;
    private Path dir;

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

    public WatchDirectory(Path dir) {
        this.dir = dir;
    }


    public void init() throws IOException {
        watchService = FileSystems.getDefault().newWatchService();

        dir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
    }

    @Override
    public void run() {
        for (;;) {
            WatchKey key;
            try {
                key = watchService.take();
            } catch (InterruptedException e) {
                break;
            }

            for (WatchEvent<?> watchEvent : key.pollEvents()) {

                WatchEvent.Kind<?> kind = watchEvent.kind();
                if (kind == OVERFLOW) continue;
                if (kind == ENTRY_CREATE) {
                    System.out.println("File created : " + watchEvent.context());
                } else if (kind == ENTRY_MODIFY) {
                    System.out.println("File modified : " + watchEvent.context());
                } else if (kind == ENTRY_DELETE) {
                    System.out.println("File deleted : " + watchEvent.context());
                }

                key.reset();
            }
        }
    }

    @Override
    public void close() throws IOException {
        watchService.close();
    }
}
