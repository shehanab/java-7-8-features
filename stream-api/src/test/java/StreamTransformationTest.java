import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Shehan on 5/27/15.
 */
public class StreamTransformationTest {

    private Stream<Integer> stream;

    @Before
    public void setUp() throws Exception {
        final int[] i = {0};
        stream = Stream.generate(() -> ++i[0]).limit(100);
    }

    @Test
    public void testMap() throws Exception {
//        stream.map(integer -> "Value : " + String.valueOf(integer)).forEach(System.out::println);
        List<Integer> collect = stream.collect(toList());

//        for (Integer integer : collect) {
//            String x = "Value : " + String.valueOf(integer);
//            System.out.println(x);
//        }

        collect.stream()
                .map(integer -> "Value : " + String.valueOf(integer))
                .forEach(System.out::println);
    }

    @Test
    public void testFilter() throws Exception {
//        stream.filter(integer -> integer % 2 == 0).peek(System.out::println);

        List<Integer> collect = stream.collect(toList());

        for (Integer integer : collect) {
            if (integer % 2 == 0) {
                System.out.println(integer);
            }
        }

        collect.stream()
                .filter(integer -> integer % 2 == 0)
                .forEach(System.out::println);

    }

    @Test
    public void testPeek() throws Exception {

        stream.peek(integer -> System.out.println("value : " + integer))
                .forEach(integer1 -> {
                });

    }

    @Test
    public void testSkipAndLimit() throws Exception {
        stream.skip(10).limit(20).forEach(System.out::println);
    }

    @Test
    public void testDistinct() throws Exception {
        stream.peek(System.out::println).distinct().forEach(System.out::println);
    }

    @Test
    public void testSorted() throws Exception {
        stream.peek(System.out::println)
                .sorted(Comparator.<Integer>naturalOrder())
                .limit(100)
                .forEach(System.out::println);
    }

}
