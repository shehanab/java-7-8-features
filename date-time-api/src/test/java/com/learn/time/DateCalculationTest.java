package com.learn.time;

import org.junit.Test;

import java.time.*;

/**
 * Created by Shehan on 6/17/15.
 */
public class DateCalculationTest {

    @Test
    public void testDateCalculation() throws Exception {

        System.out.println(LocalDateTime.now().plusMinutes(5));
    }

    @Test
    public void testDuration() throws Exception {
        LocalDateTime lastDay = LocalDateTime.now().withMonth(12).withDayOfMonth(31);

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.ofDays(7);
        System.out.println(duration.toMinutes());
//        System.out.println(Duration.between(now, lastDay));
    }

    @Test
    public void testPeriod() throws Exception {
        LocalDate lastDay = LocalDate.now().withMonth(12).withDayOfMonth(31);

        LocalDate now = LocalDate.now();

        Period period = Period.between(lastDay, now);

        System.out.println(period);
    }

    @Test
    public void testCompare() throws Exception {
        LocalDateTime lastDay = LocalDateTime.now().withMonth(12).withDayOfMonth(31);

        LocalDateTime now = LocalDateTime.now();

        System.out.println(lastDay.isAfter(now));
    }

    @Test
    public void testInstance() throws Exception {

        Instant now = Instant.now();

        LocalDateTime time = LocalDateTime.now();
        Instant instant = time.toInstant(ZoneOffset.of("+0530"));
        System.out.println(instant);
    }
}
