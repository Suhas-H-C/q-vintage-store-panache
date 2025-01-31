package org.shc.quarkus.jpa.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.shc.quarkus.jpa.entity.Customer;

@ApplicationScoped
public class CustomerRepository {

    @Inject
    EntityManager em;

    @Transactional
    public void persistCustomer(Customer customer) {
        em.persist(customer);
    }

    @Transactional
    public Customer getCustomerById(Long id) {
        return em.find(Customer.class, id);
    }
}