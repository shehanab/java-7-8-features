package com.learn;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class PersonTestHelperTest {

    private PersonTestHelper helper;

    @Before
    public void setUp() throws Exception {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("data.json");
        Person[] persons = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create().fromJson(new InputStreamReader(resourceAsStream), Person[].class);
        helper = new PersonTestHelper(Arrays.asList(persons));
    }

    @Test
    public void testMultipleParameters() throws Exception {
        List<Person> persons = helper.searchBy((name, age, phone) -> age > 25);
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    @Test
    public void testMethodReference() throws Exception {

        List<Person> persons = helper.searchBy(this::filterByParams);
        for (Person person : persons) {
            System.out.println(person);
        }

    }

    private boolean filterByParams(String name, int age, String phone) {
        return age > 25;
    }

    @Test
    public void testSearchBy2Params() throws Exception {
        int maxAge = 20;

        List<Person> persons = helper
                .searchBy(filterByPerson(maxAge));
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    private PersonTestHelper.SearchCriteria2 filterByPerson(int maxAge) {
        return person -> person.getPhone().startsWith("+9477")
                        && person.getAge() < maxAge;
    }
}