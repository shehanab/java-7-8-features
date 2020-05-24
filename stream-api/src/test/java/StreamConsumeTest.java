import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Shehan on 5/27/15.
 */
public class StreamConsumeTest {

    private Stream<Integer> stream;

    @Before
    public void setUp() throws Exception {
        stream = Stream.iterate(1, i -> ++i).limit(10);
    }

    @Test
    public void testApplyConsumer() throws Exception {
        stream.forEach(System.out::println);
    }

    @Test
    public void testCollectToCollection() throws Exception {

        List<Integer> collected = stream.collect(toList());
        System.out.println(collected);
    }

    @Test
    public void testQueryValues() throws Exception {

        List<Integer> collected = stream.collect(toList());
        System.out.println("Count : " + collected.stream().count());
        System.out.println("First : " + collected.stream().findFirst());
        System.out.println("Any : " + collected.stream().findAny());
        System.out.println("Max : " + collected.stream().max(Comparator.naturalOrder()));
        System.out.println("Min : " + collected.stream().min(Comparator.naturalOrder()));
        System.out.println("Any negative : " + collected.stream().anyMatch(integer -> integer < 0));
        System.out.println("Any above 99 : " + collected.stream().anyMatch(integer -> integer > 99));
        System.out.println("All positive : " + collected.stream().allMatch(integer -> integer > 0));
        System.out.println("All above 50 : " + collected.stream().allMatch(integer -> integer > 50));
    }

    @Test
    public void testReduction() throws Exception {
        System.out.println(stream.reduce(1, (a, b) -> a * b));
    }
}
