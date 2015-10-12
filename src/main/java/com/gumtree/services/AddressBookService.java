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

}
