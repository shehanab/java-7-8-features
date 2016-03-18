package com.learn.time;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.*;

/**
 * Created by Shehan on 6/24/15.
 */
public class TemporalTest {

    /**
     * Field based accessor to manipulate date.
     * @throws Exception
     */
    @Test
    public void testTemporalField() throws Exception {

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.get(ChronoField.CLOCK_HOUR_OF_DAY));
    }

    @Test
    public void testTemporalUnit() throws Exception {

        System.out.println(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));
    }

    @Test
    public void testTemporalQuery() throws Exception {

        System.out.println(LocalDateTime.now().query(TemporalQueries.precision()));
    }

    @Test
    public void testTemporalAmount() throws Exception {

        System.out.println(LocalDateTime.now().minus(Duration.of(1, ChronoUnit.DAYS)));
    }

    @Test
    public void testTemporalAdjuster() throws Exception {

        TemporalAdjuster lastFriday = TemporalAdjusters.previous(DayOfWeek.FRIDAY);
        System.out.println(LocalDateTime.now().with(lastFriday));

    }
}
