package org.shc.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.shc.quarkus.jpa.entity.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@TestTransaction
public class CustomerRepositoryTest {

    private final CustomerRepository repository = new CustomerRepository();

    @Test
    void shouldCreateCustomerAndReturnWhenIdIsPassed() {
        Customer customer = new Customer("firstname1", "lastname1", "email1");
        repository.persist(customer);
        assertNotNull(customer.id);
        Customer customerById = repository.findById(customer.id);
        assertEquals(customer.getFirstName(), customerById.firstName);
    }

    @Test
    void shouldPersistCustomerAndReturnSortedCustomers() {
        Customer customer1 = new Customer("firstname2", "lastname2", "email2");
        Customer customer2 = new Customer("firstname3", "lastname3", "email3");
        Customer customer3 = new Customer("firstname4", "lastname4", "email4");
        repository.persist(customer1);
        repository.persist(customer2);
        repository.persist(customer3);
        assertEquals("firstname4", repository.getCustomersSorted().get(0).firstName);
    }
}
