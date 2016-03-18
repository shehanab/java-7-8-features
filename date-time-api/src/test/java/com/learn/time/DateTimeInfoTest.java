package com.learn.time;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.junit.Assert.*;

/**
 * Created by Shehan on 6/15/15.
 */
public class DateTimeInfoTest {

    @Test
    public void testLocalDate() throws Exception {
        System.out.println(LocalDate.now());
    }

    @Test
    public void testLocalTime() throws Exception {
        System.out.println(LocalTime.now());
    }

    @Test
    public void testLocalDateTime() throws Exception {
        System.out.println(LocalDateTime.now());
    }

    @Test
    public void testEpochDay() throws Exception {
        System.out.println(LocalDate.ofEpochDay(0));
    }

    @Test
    public void testEpochDayTime() throws Exception {
        System.out.println(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHoursMinutes(5, 30)));
    }

    @Test
    public void testReplace() throws Exception {
        System.out.println(LocalDateTime.now().withMonth(11).withDayOfMonth(16));
    }

    @Test
    public void testCalculate() throws Exception {
        System.out.println(LocalDateTime.now().plusDays(1));
    }

    @Test
    public void testAdjuster() throws Exception {
        System.out.println(LocalDateTime.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY)));
    }

    @Test
    public void testTruncation() throws Exception {
        System.out.println(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
    }

    @Test
    public void testTimeZones() throws Exception {
        ZoneId colomboZoneId = ZoneId.of("Asia/Colombo");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), colomboZoneId);
        System.out.println(zonedDateTime);
        String str = zonedDateTime.toString();
        System.out.println(ZonedDateTime.parse(str));
    }

    @Test
    public void testOffsetTime() throws Exception {

    }

    @Test
    public void testPeriod() throws Exception {
        Period period = Period.of(3, 2, 1);
        System.out.println(LocalDate.now().plus(period));
    }

    @Test
    public void testCreatePeriod() throws Exception {
        LocalDate firstSunday = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
        System.out.println(Period.between(firstSunday, LocalDate.now()).getDays());
    }

    @Test
    public void testDuration() throws Exception {
        Duration duration = Duration.ofMinutes(5);
        System.out.println(LocalTime.now().plus(duration));
    }

    @Test
    public void testCreateDuration() throws Exception {
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.NOON);

        System.out.println(Duration.between(LocalDateTime.now(), dateTime));
    }

    @Test
    public void testListAllZoneIds() throws Exception {
        ZoneId.getAvailableZoneIds().forEach(System.out::println);

    }
}