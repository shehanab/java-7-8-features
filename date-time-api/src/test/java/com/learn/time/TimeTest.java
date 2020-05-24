package com.learn.time;

import junit.framework.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Shehan on 6/17/15.
 */
public class TimeTest {

    @Test
    public void testDateClasses() throws Exception {
        System.out.println(LocalDate.now());
        System.out.println(YearMonth.now());
        System.out.println(MonthDay.now());
    }

    @Test
    public void testTimeClasses() throws Exception {
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
    }

    @Test
    public void testLocalDateTime() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime x = now.withMonth(Month.DECEMBER.getValue()).plusMonths(1);
        System.out.println(x.getMonthValue());
        System.out.println(now.truncatedTo(ChronoUnit.HOURS));
    }

    @Test
    public void testZones() throws Exception {

        LocalDateTime time = LocalDateTime.now();

        ZonedDateTime zoned = time
                .atZone(ZoneId.systemDefault())
                .withZoneSameInstant(ZoneId.of("Asia/Singapore"));
        System.out.println(zoned);
//        ZoneId.getAvailableZoneIds().stream().filter(s -> s.contains("Sing")).forEach(System.out::println);
    }

    @Test
    public void testLocalDateTimeMethods() throws Exception {

        LocalDateTime now = LocalDateTime.now();

        Assert.assertEquals(now.get(ChronoField.DAY_OF_MONTH), now.getDayOfMonth());
        assertEquals(now.minus(Duration.ofHours(5)), now.minusHours(5));
        assertEquals(now.minusHours(5), now.minus(5, ChronoUnit.HOURS));
        Assert.assertEquals(now.with(ChronoField.DAY_OF_MONTH, 5), now.withDayOfMonth(5));
        System.out.println(now.truncatedTo(ChronoUnit.HALF_DAYS));
        System.out.println(now.with(TemporalAdjusters.firstDayOfNextMonth()));
        Instant instant = now.toInstant(ZoneOffset.UTC);
        Instant instant1 = ZonedDateTime.now().toInstant();

        System.out.println(instant.isSupported(ChronoUnit.CENTURIES));
//        System.out.println(instant.plus(1, ChronoUnit.CENTURIES));

        java.util.Date from = Date.from(instant1);

        Instant temporal = Instant.ofEpochMilli(from.getTime());
        System.out.println(temporal.atZone(ZoneId.of("Asia/Colombo")).toLocalDateTime());
    }


    @Test
    public void testZone() throws Exception {

//        ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Africa/Nairobi"));
        System.out.println(now);

        System.out.println(now.getOffset());

        System.out.println(LocalDateTime.now().atOffset(ZoneOffset.of("+12:30")));

        System.out.println(OffsetTime.now(ZoneId.systemDefault()));
    }

    @Test
    public void testDateCalculations() throws Exception {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plus = now.plus(Duration.ofHours(1));
        LocalDateTime minus = now.minus(Duration.ofHours(1));
        now.with(TemporalAdjusters.firstDayOfYear());

        Duration duration = Duration.between(now, now.withDayOfMonth(1));
        System.out.println(duration);

        Period period = Period.between(now.toLocalDate(), now.plusYears(1).toLocalDate());
        System.out.println(period);

        Duration duration1 = Duration.of(3, ChronoUnit.HOURS).plus(Duration.of(22, ChronoUnit.MINUTES));
        System.out.println(now.plus(duration1));

        System.out.println(now.minus(period));

        LocalDateTime before1hour = now.minusHours(1);
        LocalDateTime after1hour = now.plusHours(1);

        boolean b = before1hour.isAfter(now) && after1hour.isBefore(now);
    }
}
