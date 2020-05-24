package com.learn;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.learn.domain.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by Shehan on 6/15/15.
 */
public class TransactionTest {

    private List<Transaction> transactions;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.json");
        transactions = new GsonBuilder()
                .setDateFormat("MM-dd-yy:HH:mm:ss")
                .create()
                .fromJson(new InputStreamReader(inputStream),
                        new TypeToken<List<Transaction>>() {
                        }.getType());

    }

    @Test
    public void testReadAll() throws Exception {
        transactions.forEach(System.out::println);
    }

    @Test
    public void testAverageOfTransactionsAbove5() throws Exception {
        transactions.stream()
                .filter(t -> t.getAmount() > 5)
                .mapToDouble(Transaction::getAmount)
                .average()
                .ifPresent(System.out::println);

    }

    @Test
    public void testFind() throws Exception {

        String msisdn = "(073)0300190";

        Optional<Transaction> transaction = transactions.stream()
                .filter(t -> msisdn.equals(t.getFrom())).findAny();

        String msg = transaction
                .map(convert("Found"))
                .orElseGet(() -> "Transaction not found for msisdn " + msisdn);

        System.out.println(msg);
        String message = transaction
                .map(convert("Found"))
                .orElse("No Transaction Found");

        System.out.println(message);
        System.out.println(transaction);
    }

    private Function<Transaction, String> convert(String prefix) {
        return t -> prefix + " " + t.toString();
    }

    @Test
    public void testGetAverageAmount() throws Exception {


    }
}
