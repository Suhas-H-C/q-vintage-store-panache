package org.shc.quarkus.panache.entity;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.shc.quarkus.jpa.entity.Customer;
import org.shc.quarkus.panache.repository.CustomerRepository;

import java.time.Instant;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.shc.quarkus.panache.utils.ArtistUtils.michealJackson;
import static org.shc.quarkus.panache.utils.BookUtils.dancersColony;
import static org.shc.quarkus.panache.utils.CustomerUtils.JohnDoe;
import static org.shc.quarkus.panache.utils.PublisherUtils.SS;
import static org.shc.quarkus.panache.utils.TestUtils.CLOCK;

@QuarkusTest
@TestTransaction
public class PurchaseOrderRepositoryTest {

    private final CustomerRepository customerRepository = new CustomerRepository();

    @Test
    void shouldCreateAndFindPurchaseOrder() {
        Book book = dancersColony();
        //set the artist and publisher
        book.artist = michealJackson();
        book.publisher = SS();
        book.persist();

        //create a customer
        Customer customer = JohnDoe();
        customerRepository.persist(customer);

        //create an order line
        OrderLine orderLine = new OrderLine();
        orderLine.item = book;
        orderLine.quantity = 2;

        //create a purchase order
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.customer = customer;
        purchaseOrder.createdDate = Instant.now(CLOCK);
        purchaseOrder.date = LocalDate.now(CLOCK);
        purchaseOrder.addOrderLine(orderLine);

        //persist the purchase order
        purchaseOrder.persist();

        PurchaseOrder purchaseOrderById = PurchaseOrder.findById(purchaseOrder.id);
        Book dancersColony = (Book) purchaseOrderById.orderLines.get(0).item;

        //assertions
        assertNotNull(purchaseOrder.id);
        assertThat(purchaseOrderById)
                .usingRecursiveAssertion()
                .isEqualTo(purchaseOrder);
        assertEquals(1, purchaseOrderById.orderLines.size());
        assertEquals(customer.firstName, purchaseOrderById.customer.firstName);
        assertEquals(book.publisher.name, dancersColony.publisher.name);
    }
}
