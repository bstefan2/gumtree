package com.gumtree;

import com.gumtree.model.Person;
import com.gumtree.services.AddressBookService;
import com.gumtree.services.AddressBookServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Test container for Address Book
 */
public class AddressBookTest {
    private AddressBookService addressBookService;

    @Before
    public void setUp() {
        addressBookService = new AddressBookServiceImpl("src/test/resources/AddressBook");
    }

    /**
     * Tests for how many males are in the address book feature
     */

    @Test
    public void testMaleRecordCount() {
        assertEquals(3, addressBookService.getCount(Person.Sex.Male));
    }

    @Test
    public void testMaleRecordCountNullInput() {
        assertEquals(5, addressBookService.getCount(null));
    }

    /**
     * Tests for who is the oldest person in the address book
     */
    @Test
    public void testOldestPersonInAddressBook() {
        assertEquals("Wes Jackson", addressBookService.getOldest().getName());
    }

    @Test
    public void testOldestPersonInEmptyAddressBook() {
        addressBookService = new AddressBookServiceImpl("src/test/resources/EmptyAddressBook");
        assertNull(null, addressBookService.getOldest());
    }
}
