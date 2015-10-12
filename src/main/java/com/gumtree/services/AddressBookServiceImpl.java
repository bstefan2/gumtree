package com.gumtree.services;

import com.gumtree.model.mappers.PersonMapper;
import com.gumtree.exceptions.OperationException;
import com.gumtree.model.Person;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;

public class AddressBookServiceImpl implements AddressBookService {
    /** datasource path */
    private String datasourceURI;

    /**
     * Creates an AddressBookService out of a specified file
     * @param datasourceURI the path of file from which the datasource is instantiated
     */
    public AddressBookServiceImpl(String datasourceURI) {
        this.datasourceURI = datasourceURI;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public long getCount(Person.Sex bySex) {
        /** get the stream from the file */
        try (Stream<String> lines = Files.lines(Paths.get(datasourceURI))) {
            Function<String, Person> mapLineToPerson = new PersonMapper();

            if (bySex != null) {
                /** return the count based on the person's sex */
                return lines.
                        map(mapLineToPerson).
                        filter(person -> person.getSex().equals(bySex)).
                        count();
            } else {
                /** return all */
                return lines.
                        map(mapLineToPerson).
                        count();
            }
        } catch (Exception e) {
            throw new OperationException(e);
        }
    }
}
