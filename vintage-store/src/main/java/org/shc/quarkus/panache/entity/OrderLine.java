package org.shc.quarkus.panache.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Sort;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;


@Entity
@Table(name = "t_purchase_order_lines")
public class OrderLine extends PanacheEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "item_fk")
    public Item item;

    @Column(nullable = false)
    public Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_order_fk")
    @JsonbTransient
    public PurchaseOrder purchaseOrder;

    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();

    public static List<OrderLine> findByQuantity(Integer quantity) {
        return list("quantity=?1", Sort.by("createdDate"), quantity);
    }
}
