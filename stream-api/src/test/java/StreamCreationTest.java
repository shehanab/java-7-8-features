import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Shehan on 5/27/15.
 */
public class StreamCreationTest {

    @Test
    public void testCreateFromCollection() throws Exception {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        LocalDateTime start = LocalDateTime.now();
        integers.forEach(System.out::println);
        LocalDateTime end = LocalDateTime.now();
        System.out.println(Duration.between(start, end));

    }

    @Test
    public void testCreateFromArray() throws Exception {
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        IntStream stream = Arrays.stream(ints);
        stream.forEach(System.out::println);
    }

    @Test
    public void testCreateFromFactory() throws Exception {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        stream.forEach(System.out::println);

        final int[] i = {0};
        Stream<Integer> integerStream = Stream.generate(() -> ++i[0]).limit(10);
        Stream<Integer> iterate = Stream.iterate(0, a -> a++).limit(10);
        IntStream range = IntStream.range(0, 100);
        range.forEach(System.out::println);
    }

    @Test
    public void testCreateFromFile() throws Exception {
        // String file = getClass().getClassLoader().getResource("test.txt").getFile();
        String file = Objects.requireNonNull(getClass().getClassLoader().getResource("test.txt")).getFile();
        Stream<String> lines = Files.lines(Paths.get(file));
        lines.forEach(System.out::println);
    }
}
