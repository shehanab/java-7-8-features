import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by Shehan on 5/27/15.
 */
public class CollectorsTest {

    private Stream<Integer> stream;

    @Before
    public void setUp() throws Exception {
        final int[] i = {0};
        stream = Stream.generate(() -> ++i[0]).limit(10);
    }

    @Test
    public void testCollectToList() throws Exception {

        List<Integer> collected = stream.collect(toList());
        System.out.println(collected);

    }

    @Test
    public void testReduction() throws Exception {

        Optional<Integer> collected = stream.collect(reducing((a, b) -> a + b));
        System.out.println(collected);

    }

    @Test
    public void testSumming() throws Exception {

        Integer collected = stream.collect(Collectors.summingInt(a -> a));
        System.out.println(collected);

    }

    @Test
    public void testCollectToMap() throws Exception {

        Map<Integer, String> collected = stream.collect(toMap(a -> a, a -> "Value is " + a));
        System.out.println(collected);

    }

    @Test
    public void testPartitioning() throws Exception {

        Map<Boolean, List<Integer>> collected = stream.collect(partitioningBy(a -> a % 2 == 0));
        System.out.println(collected);

    }

    @Test
    public void testGroupingBy() throws Exception {

        Map<Integer, List<Integer>> collected = stream.collect(groupingBy(a -> a % 3));
        System.out.println(collected);

    }

    @Test
    public void testJoining() throws Exception {

        String collected = stream.map(Object::toString).collect(joining(", ", "[", "]"));
        System.out.println(collected);

    }
}
