package com.learn;

import com.learn.MsisdnGenerator;
import org.junit.Test;

import java.util.PrimitiveIterator;
import java.util.stream.LongStream;

/**
 * Created by Shehan on 5/29/15.
 */
public class MsisdnGeneratorTest {

    @Test
    public void testNumberList() throws Exception {
        MsisdnGenerator generator = new MsisdnGenerator("1,2,3,4,5,6");
        generator.stream().limit(10).forEach(System.out::println);
    }

    @Test
    public void testNumberRanges() throws Exception {
        MsisdnGenerator generator = new MsisdnGenerator("10-1, 1-10");
//        generator.stream().forEach(System.out::println);
        PrimitiveIterator.OfLong iterator = generator.stream().limit(100).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testNumberRangeAndNumberMix() throws Exception {
        MsisdnGenerator generator = new MsisdnGenerator("1,2,6-10,22,32,10,40-50");
        generator.stream().limit(100).forEach(System.out::println);
    }

    @Test
    public void testWildcard() throws Exception {
        MsisdnGenerator generator = new MsisdnGenerator("947712312*");
        generator.stream().limit(100).forEach(System.out::println);
    }

    @Test
    public void testRange() throws Exception {
        LongStream.rangeClosed(1, 10).forEach(System.out::println);

    }
}