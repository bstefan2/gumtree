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

        return new Person(
                details[0].trim(),
                Person.Sex.valueOf(details[1].trim()),
                LocalDate.parse(details[2].trim(), DateTimeFormatter.ofPattern("dd/MM/yy"))
        );
    }
}
