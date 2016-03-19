package com.learn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shehan on 5/18/15.
 */
public class PersonTestHelper {

    private List<Person> people;

    public PersonTestHelper(List<Person> people) {
        this.people = people;
    }

    public List<Person> searchBy(SearchCriteria searchCriteria){
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : people) {
            if (searchCriteria.search(person.getName(), person.getAge(), person.getPhone())) {
                result.add(person);
            }
        }
        return result;
    }

    public List<Person> searchBy(SearchCriteria2 searchCriteria) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : people) {
            if (searchCriteria.search(person)) {
                result.add(person);
            }
        }
        return result;
    }

    public interface SearchCriteria {
        boolean search(String name, int age, String phone);
    }

    public interface SearchCriteria2 {
        boolean search(Person person);
    }
}
