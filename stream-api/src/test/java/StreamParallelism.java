import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Shehan on 5/27/15.
 */
public class StreamParallelism {

    private Stream<String> stream;

    @Before
    public void setUp() throws Exception {
        String file = getClass().getClassLoader().getResource("test.txt").getFile();
        stream = Files.lines(Paths.get(file));
    }

    @Test
    public void testParallelTime() throws Exception {
        List<String> strings = new ArrayList<>();
        Stream<String> sequencialStream = strings.stream();
        Stream<String> parallelStream = strings.parallelStream();
        logTime(
                () -> parallelStream.map(this::doToString).forEach(value -> {
                }),
                "Parallel");
        
        logTime(
                () -> sequencialStream.map(this::doToString).forEach(value -> {}),
                "Sequential");
    }

    private String doToString(String source) {
        return "length : " + source.length() + ", contains 'Yossarian'" + source.contains("Yossarian");
    }

    private void logTime(Runnable runnable, String label) {
        long start = System.nanoTime();
        runnable.run();
        System.out.println(label + " execution time : " + (System.nanoTime() - start));
    }
}
