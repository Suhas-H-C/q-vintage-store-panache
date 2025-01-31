package org.shc.quarkus.panache.entity;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.shc.quarkus.jpa.entity.Customer;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.shc.quarkus.panache.utils.BookUtils.dancersColony;
import static org.shc.quarkus.panache.utils.BookUtils.sriRamayanaDarshnamBook;
import static org.shc.quarkus.panache.utils.CustomerUtils.JohnDoe;
import static org.shc.quarkus.panache.utils.TestUtils.CLOCK;

@QuarkusTest
@TestTransaction
class OrderLineRepositoryTest {

    @Test
    void shouldCreateAnOrderLineAndReturnIt() {
        Customer customer = JohnDoe();

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.date = LocalDate.now(CLOCK);
        purchaseOrder.customer = customer;
        purchaseOrder.createdDate = Instant.now(CLOCK);

        OrderLine orderLine = new OrderLine();
        orderLine.item = dancersColony();
        orderLine.quantity = 1;
        orderLine.createdDate = Instant.now(CLOCK);
        purchaseOrder.addOrderLine(orderLine);

        orderLine.persist();
        OrderLine orderById = OrderLine.findById(orderLine.id);
        assertThat(orderById)
                .usingRecursiveAssertion()
                .isEqualTo(orderLine);
    }

    @Test
    void shouldFindOrderLinesByQuantity() {
        Customer customer = JohnDoe();

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.date = LocalDate.now(CLOCK);
        purchaseOrder.customer = customer;
        purchaseOrder.createdDate = Instant.now(CLOCK);

        OrderLine o1 = new OrderLine();
        o1.item = dancersColony();
        o1.quantity = 1;
        o1.createdDate = Instant.now(CLOCK);
        purchaseOrder.addOrderLine(o1);

        OrderLine o2 = new OrderLine();
        o2.item = sriRamayanaDarshnamBook();
        o2.quantity = 2;
        o2.createdDate = Instant.now(CLOCK);
        purchaseOrder.addOrderLine(o2);

        purchaseOrder.persist();
        o1.persist();
        o2.persist();

        List<OrderLine> orderLinesByQuantity = OrderLine.findByQuantity(2);
        assertEquals(1, orderLinesByQuantity.size());
        assertEquals(o2.item.price, orderLinesByQuantity.getFirst().item.price);
    }
}