package com.gumtree.services;

import com.gumtree.model.mappers.PersonMapper;
import com.gumtree.exceptions.OperationException;
import com.gumtree.model.Person;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    @Override
    /**
     * {@inheritDoc}
     */
    public Person getOldest() {
        /** get the stream from the file */
        try (Stream<String> lines = Files.lines(Paths.get(datasourceURI))) {
            Function<String, Person> mapLineToPerson = new PersonMapper();

            return lines.
                    map(mapLineToPerson).
                    reduce((p1,p2) -> p1.getBirthDate().isBefore(p2.getBirthDate()) ? p1 : p2).
                    orElse(null);
        } catch (Exception e) {
            throw new OperationException(e);
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public long getAgeDifference(String firstPersonName, String secondPersonName) {
        /** get the two dates for each person */
        LocalDate firstDate = getPersonBirthday(firstPersonName);
        LocalDate secondDate = getPersonBirthday(secondPersonName);

        /** get the actual days number */
        return ChronoUnit.DAYS.between(firstDate, secondDate);
    }

    private LocalDate getPersonBirthday(String forename) {
        /** get the stream from the file */
        try (Stream<String> lines = Files.lines(Paths.get(datasourceURI))) {
            Function<String, Person> datasourceRecordToPerson = new PersonMapper();

            return lines.
                    map(datasourceRecordToPerson).
                    filter(person -> person.getName().startsWith(forename)).
                    map(Person::getBirthDate).
                    findFirst().
                    orElseThrow(() -> new IllegalArgumentException("Person " + forename + " does not exist"));
        } catch (Exception e) {
            throw new OperationException(e);
        }
    }

}
