package org.shc.quarkus.jpa.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.shc.quarkus.jpa.entity.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class CustomerRepositoryTest {

    @Inject
    private CustomerRepository repository;

    @Test
    @TestTransaction
    void shouldCreateAndFindACustomer() {
        Customer customer = new Customer("John", "Doe", "John.Doe@email.com");
        repository.persistCustomer(customer);
        assertNotNull(customer.getId());
        Customer customerById = repository.getCustomerById(customer.getId());
        assertEquals(customer.getFirstName(), customerById.getFirstName());
    }
}