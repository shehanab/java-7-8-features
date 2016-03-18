package com.learn.time;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Shehan on 6/17/15.
 */
public class FormattingTest {

    @Test
    public void testFormatting() throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println(LocalDateTime.now().format(formatter));

    }

    @Test
    public void testParsing() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateStr = LocalDateTime.now().format(formatter);
        System.out.println(LocalDateTime.parse(dateStr, formatter));

    }
}
