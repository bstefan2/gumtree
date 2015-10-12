package com.gumtree.services;

import com.gumtree.exceptions.OperationException;
import com.gumtree.model.Person;

/**
 * AddressBook specific operations
 */
public interface AddressBookService {
    /**
     * Gets how many persons of a specific sex are in the address book
     * @param bySexFilter filter by sex
     * @return how many persons of a specific sex are in the address book
     * @throws OperationException
     * @see Person.Sex
     */
    long getCount(Person.Sex bySexFilter);

    /**
     * Gets the first oldest person in the address book
     * @return the oldest person in the address book. returns null if there is no person
     */
    Person getOldest();


    /**
     * Gets the age difference between two persons given their first name.
     * A negative result means that <code>secondForename</code> is younger than <code>firstForname</code>
     * @param firstForename first person's forename
     * @param secondForename second person's forename
     * @return the age difference between two persons given their first name
     */
    long getAgeDifference(String firstForename, String secondForename);
}
