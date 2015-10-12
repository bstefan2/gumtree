package com.gumtree.model.mappers;

import com.gumtree.model.Person;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * Builds a Person object out of a string
 */
public class PersonMapper implements Function<String, Person> {

    @Override
    public Person apply(String raw) {
        String[] details = raw.split(",");

        /**
         * make sure you convince the product owner here to keep the year as 4 digit figure.
         * even though there may be a small chance there still may be people born in 1912 and we
         * want to make a difference from the ones borned in 2012 ;)
         */
        return new Person(
                details[0].trim(),
                Person.Sex.valueOf(details[1].trim()),
                LocalDate.parse(details[2].trim(), DateTimeFormatter.ofPattern("dd/MM/yy"))
        );
    }
}
